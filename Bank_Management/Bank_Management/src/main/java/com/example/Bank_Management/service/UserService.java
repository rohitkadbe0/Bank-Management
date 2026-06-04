package com.example.Bank_Management.service;

 import com.example.Bank_Management.entity.User;
import com.example.Bank_Management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    //load a user from the database during login/authentication and convert that user into a Spring Security UserDetails object.
    public UserDetails loadUserByUsername(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles().stream()
                        .map(role -> role.replace("ROLE_", ""))
                        .toArray(String[]::new))
                .build();
    }

    // 📝 Register User
    public User register(User user) {

        System.out.println("USERNAME FROM REQUEST: " + user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(List.of("CUSTOMER")); // default role
        }
        return userRepository.save(user);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public Optional<User> findByUserName(String name) {
        return userRepository.findByUsername(name);
    }
 }
