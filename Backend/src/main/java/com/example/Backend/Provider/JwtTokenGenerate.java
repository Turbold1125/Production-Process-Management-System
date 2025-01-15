//package com.example.Backend.Provider;
//
//
//import com.example.Backend.Model.Entity.User;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//import java.util.*;
//
//@Component
//public class JwtTokenGenerate {
//
//    @Value("${jwt.secret}")
//    private String SECRET_KEY;
//
//    @Value("${jwt.issuer}")
//    private String ISSUER;
//
//    @Value("${jwt.expireInMinutes}")
//    private int expireInMinutes;
//
//    public User getCurrentUser() {
//        // Return the authenticated user as a User object
//        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    }
//
//    public Object getClaimFromToken(String token, String claimKey) {
//        Claims claims = extractAllClaims(token);
//        return claims.get(claimKey);
//    }
//
//    public String generateToken(List<Claims> additionalClaims) {
//        Map<String, Object> claims = new HashMap<>();
//
//        // Add additional claims if available
//        if (additionalClaims != null && !additionalClaims.isEmpty()) {
//            for (Claims claim : additionalClaims) {
//                claims.putAll(claim);
//            }
//        }
//
//        return createToken(claims, null);
//    }
//
//    // Generate a JWT token based on User object and claims
//    public String generateToken(User user, Map<String, Object> additionalClaims) {
//        Map<String, Object> claims = new HashMap<>();
//
//        // Add user-specific claims
//        claims.put("userId", user.getId());
//        claims.put("username", user.getEmail());
//
//        if (additionalClaims != null) {
//            claims.putAll(additionalClaims);
//        }
//
//        return createToken(claims, user.getEmail());
//    }
//
//    // Token creation logic
//    private String createToken(Map<String, Object> claims, String subject) {
//        Date now = new Date();
//        Date expiration = new Date(now.getTime() + expireInMinutes * 60 * 1000); // Expiration in minutes
//
//        System.out.println("Creating JWT Token...");
//        System.out.println("Claims: " + claims);
//        System.out.println("Subject: " + subject);
//        System.out.println("Issuer: " + ISSUER);
//        System.out.println("Issued at: " + now);
//        System.out.println("Expiration: " + expiration);
//
//        String token = Jwts.builder()
//                .setClaims(claims)
//                .setSubject(subject)
//                .setIssuer(ISSUER)
//                .setIssuedAt(now)
//                .setExpiration(expiration)
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                .compact();
//
//        System.out.println("Generated Token::::::::::::: " + token);
//
//        return token;
//    }
//
//    // Validate the token and check if it's not expired and matches the email
//    public Boolean validateToken(String token, String email) {
//        final String username = extractUsername(token);
//        return (username.equals(email) && !isTokenExpired(token));
//    }
//
//    // Extract the username from the JWT token
//    private String extractUsername(String token) {
//        return extractAllClaims(token).getSubject();
//    }
//
//    // Extract all claims from the JWT token
//    public Claims extractAllClaims(String token) {
//        return Jwts.parser()
//                .setSigningKey(SECRET_KEY)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    // Check if the token is expired
//    private Boolean isTokenExpired(String token) {
//        return extractAllClaims(token).getExpiration().before(new Date());
//    }
//
//    // Extract the user's role from the JWT token
//    public String extractRole(String token) {
//        return extractAllClaims(token).get("role", String.class);
//    }
//}
