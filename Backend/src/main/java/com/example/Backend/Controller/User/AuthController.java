package com.example.Backend.Controller.User;


import com.example.Backend.Api.User.AuthApi;
import com.example.Backend.DTOs.User.AuthenticationRequest;
import com.example.Backend.Exception.ServiceException;
import com.example.Backend.DTOs.User.AuthResponse;
import com.example.Backend.DTOs.User.RegisterRequest;
import com.example.Backend.Service.User.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthService authService;


    @PostMapping("register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) throws ServiceException {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthenticationRequest request) throws ServiceException {
        return authService.login(request);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam(required = true) Long id) throws ServiceException {
        authService.delete(id);
    }

//    @PostMapping("/register")
//    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) throws ServiceException {
//
//        return ResponseEntity.ok(authService.register(request));
//    }

//    @PostMapping("/login")
//    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
//
//        return ResponseEntity.ok(authService.login(request));
//    }
}
