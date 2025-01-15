package com.example.Backend.Service.User;

import com.example.Backend.DTOs.User.AuthenticationRequest;
import com.example.Backend.DTOs.User.AuthResponse;
import com.example.Backend.DTOs.User.RegisterRequest;
import com.example.Backend.Exception.ErrorResponse;
import com.example.Backend.Exception.ServiceException;
import com.example.Backend.Model.User.User;
import com.example.Backend.Provider.JwtService;
import com.example.Backend.Repo.User.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthResponse register(@Valid RegisterRequest request) throws ServiceException {

        logger.info("Attempting to register user with email: {}", request.getEmail());

        if (request.getRole() == null || request.getEmail() == null || request.getPassword() == null) {
            throw new ServiceException(ErrorResponse.VALIDATION_ERROR);
        }

        if (request.getEmail() != null && userRepository.findByEmail(request.getEmail()).isPresent()) {
            logger.error("Registration failed: Email {} already exists", request.getEmail());
            throw new ServiceException(ErrorResponse.EMAIL_ALREADY_EXIST);
        }

        User user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);

        logger.info("User registered successfully: {}", request.getEmail());

        String jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .user(user)
                .token(jwtToken)
                .build();
    }

    public AuthResponse login(AuthenticationRequest request) throws ServiceException {

        if (request.getEmail() == null || request.getPassword() == null) {
            throw new ServiceException(ErrorResponse.VALIDATION_ERROR);
        }

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ServiceException(ErrorResponse.NO_CUSTOMER));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ServiceException(ErrorResponse.AUTH);
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .user(user)
                .token(jwtToken)
                .build();
    }

    public void delete(Long id) throws ServiceException {
        if (!userRepository.existsById(id)) {
            throw new ServiceException(ErrorResponse.NO_CUSTOMER);
        }
        userRepository.deleteById(id);
    }
}