//package com.example.Backend.Provider;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.util.stream.Collectors;
//
//@Component
//public class LoggingInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
//        String requestBody = new BufferedReader(request.getReader()).lines().collect(Collectors.joining("\n"));
//        System.out.println("Request URL::::::::: " + request.getMethod() + " ---- "+ request.getRequestURL());
//        System.out.println("Request Body: " + requestBody);
//        return true;
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws IOException {
//        System.out.println("Response Status: " + response.getStatus());
//    }
//}