package dev.gergely.springbootneo4j.services;

import dev.gergely.springbootneo4j.models.User;
import dev.gergely.springbootneo4j.repositories.UserRepository;
import dev.gergely.springbootneo4j.requests.CreateuserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(CreateuserRequest request){
        User user = new User();

        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setRoles(request.getRoles());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        return user;
    }
}
