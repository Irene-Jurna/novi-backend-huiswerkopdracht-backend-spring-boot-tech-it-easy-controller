package nl.novi.techItEasy.config;

import nl.novi.techItEasy.filter.JwtRequestFilter;
import nl.novi.techItEasy.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    public final CustomUserDetailsService customUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;
    private final PasswordEncoder passwordEncoder;

    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter, PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.passwordEncoder = passwordEncoder;
    }

    // Authenticatie met customUserDetailsService en passwordEncoder
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        PasswordEncoderConfig passwordEncoderConfig = new PasswordEncoderConfig();
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    // Authorizatie met jwt
    @Bean
    protected SecurityFilterChain filter (HttpSecurity http) throws Exception {

        //JWT token authentication
        http
                .csrf().disable()
                .httpBasic().disable()
                .cors().and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                .requestMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST,"/users/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                .requestMatchers("/cimodules", "/remotes", "/wallbrackets", "/televisions", "/televisions/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/authenticated").authenticated()
                .requestMatchers("/authenticate").permitAll()
                .anyRequest().denyAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                // Spring security is een verzameling filters. Er zijn er meer, die kun je opzoeken.
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
