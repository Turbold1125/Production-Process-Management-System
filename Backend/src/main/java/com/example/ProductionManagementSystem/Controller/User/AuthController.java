package com.example.ProductionManagementSystem.Controller.User;


import com.example.ProductionManagementSystem.Api.User.AuthApi;
import com.example.ProductionManagementSystem.DTOs.User.AuthRequest;
import com.example.ProductionManagementSystem.Exception.ServiceException;
import com.example.ProductionManagementSystem.DTOs.User.AuthResponse;
import com.example.ProductionManagementSystem.DTOs.User.RegisterRequest;
import com.example.ProductionManagementSystem.Service.User.AuthService;
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
    public AuthResponse login(@Valid @RequestBody AuthRequest request) throws ServiceException {
        return authService.login(request);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam(required = true) Integer id) throws ServiceException {
        authService.delete(id);
    }

    @PostMapping("/reauth")
    public AuthResponse reAuth(@RequestBody AuthRequest request)  throws ServiceException {
        return authService.login(request);
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
