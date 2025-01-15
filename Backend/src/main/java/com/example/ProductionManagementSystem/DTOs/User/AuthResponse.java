package com.example.ProductionManagementSystem.DTOs.User;

import com.example.ProductionManagementSystem.Model.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private User user;
    private String token;
}
