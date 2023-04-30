package nl.novi.techItEasy.services;

import nl.novi.techItEasy.dtos.UserDto;
import nl.novi.techItEasy.models.Authority;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// In deze klasse niet veel spannends. Klasse implementeert de interface UserDetailsService. Die heeft alleen een loadUserByUserName. Wel belangrijk, want deze wordt in onze security gebruikt.

@SpringBootApplication
public class CustomUserDetailsService implements UserDetailsService {

    // Hier zou je ook de userRepository kunnen gebruiken. Misschien nog beter/ netter. Maar dit werkt ook. En dan krijg je een User terug waarmee je kunt werken. Nu werken we met een userDto.
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDto userDto = userService.getUser(username);

        String password = userDto.getPassword();

        Set<Authority> authorities = userDto.getAuthorities();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority: authorities) {
            // GrantedAuthorities zijn gewoon je authorities, maar dan in een herkenbaar jasje voor Spring Security
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }

        // Hier wordt de userdetails gemaakt
        // Je kunt hier ook je eigen userDetails implementeren, en niet core gebruiken
        return new org.springframework.security.core.userdetails.User(username, password, grantedAuthorities);
    }

}