package nl.novi.techItEasy.services;

import nl.novi.techItEasy.dtos.UserDto;
import nl.novi.techItEasy.exceptions.RecordNotFoundException;
import nl.novi.techItEasy.exceptions.UsernameNotFoundException;
import nl.novi.techItEasy.models.Authority;
import nl.novi.techItEasy.models.User;
import nl.novi.techItEasy.repositories.UserRepository;
import nl.novi.techItEasy.utils.RandomStringGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*moest hier niet een annotatie?*/
@Service
public class UserService {
    /*inject de juiste repository*/

    private final UserRepository userRepos;

    public UserService(UserRepository userRepos) {
        this.userRepos = userRepos;
    }

    public List<UserDto> getUsers() {
        List<UserDto> collection = new ArrayList<>();
        List<User> list = userRepos.findAll();
        for (User user : list) {
            collection.add(fromUser(user));
        }
        return collection;
    }

    public UserDto getUser(String username) {
        UserDto dto = new UserDto();
        Optional<User> user = userRepos.findById(username);
        if (user.isPresent()){
            dto = fromUser(user.get());
        }else {
            throw new UsernameNotFoundException(username);
        }
        return dto;
    }

    public boolean userExists(String username) {
        return userRepos.existsById(username);
    }

    public String createUser(UserDto userDto) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        userDto.setApikey(randomString);
        User newUser = userRepos.save(toUser(userDto));
        return newUser.getUsername();
    }

    public void deleteUser(String username) {
        /*repo*/.deleteById(username);
    }

    public void updateUser(String username, UserDto newUser) {
        if (!userRepos.existsById(username)) throw new RecordNotFoundException();
        User user = userRepos.findById(username).get();
        user.setPassword(newUser.getPassword());
        userRepos.save(user);
    }

    public Set<Authority> getAuthorities(String username) {
        if (!/*repo*/.existsById(username)) throw new /*exception*/(username);
        User user = /*repo*/.findById(username).get();
        UserDto userDto = fromUser(user);
        return userDto.getAuthorities();
    }

    public void addAuthority(String username, String authority) {

        if (!userRepos.existsById(username)) throw new /*exception*/(username);
        User user = userRepos.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepos.save(user);
    }

    public void removeAuthority(String username, String authority) {
        if (!userRepos.existsById(username)) throw new /*exception*/(username);
        User user = userRepos.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepos.save(user);
    }

    public static UserDto fromUser(User user){

        var dto = new UserDto();

        dto.username = user.getUsername();
        dto.password = user.getPassword();
        dto.enabled = user.isEnabled();
        dto.apikey = user.getApikey();
        dto.email = user.getEmail();
        dto.authorities = user.getAuthorities();

        return dto;
    }

    public User toUser(UserDto userDto) {

        var user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEnabled(userDto.getEnabled());
        user.setApikey(userDto.getApikey());
        user.setEmail(userDto.getEmail());

        return user;
    }

}
