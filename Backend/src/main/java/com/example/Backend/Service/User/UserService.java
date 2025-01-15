package com.example.Backend.Service.User;

import com.example.Backend.Exception.ServiceException;
import com.example.Backend.Model.User.User;
import com.example.Backend.Exception.ErrorResponse;
import com.example.Backend.Repo.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CurrentService currentService;
    private final PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<User> getAllUsers() throws ServiceException {

        List<User> users = userRepository.findAll();

//        User current = currentService.getCurrentUser();

//        currentService.checkUserRole(current, Role.ADMIN);

        if (users.isEmpty()) {
            throw new ServiceException(ErrorResponse.NO_CONTENT);
        }

        return users;
    }

    public void deleteUser(Long id) throws ServiceException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ErrorResponse.NO_CONTENT));

        userRepository.delete(user);
    }

    public User updateUser(Long id, User user) throws ServiceException {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ErrorResponse.NO_CONTENT));

        existingUser.setEmail(user.getEmail());
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        existingUser.setRole(user.getRole());

        return userRepository.save(existingUser);
    }

//    public List<User> getAllUsers() throws ServiceException {
//        // Get the current user's email from the JWT
//        String currentUserEmail = jwtTokenGenerate.getCurrentUser().getEmail();
//
//        // Log the email of the current user
//        logger.info("Current user email: {}", currentUserEmail);
//
//        // Check if the current user has the right role to access all users
//        if (currentUserEmail != null) {
//            logger.error("Access denied for user: {}", currentUserEmail);
//        }
//        GrantedAuthority
//        // If the user is authorized, return the list of users
//        return userRepository.findAll();
//    }

}
