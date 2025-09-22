package com.pennywise.service;

import com.pennywise.DTO.SignupRequest;
import com.pennywise.model.User;
import com.pennywise.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register new user for signup flow
    public User registerUser(SignupRequest request) throws Exception {
        if (findUserByEmail(request.getEmail()).isPresent()) {
            throw new Exception("Email already exists");
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());  // raw password passed here

        // saveUser encodes password and saves the user
        return saveUser(user);
    }

    // Save user with password encoding
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Find user by email
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
