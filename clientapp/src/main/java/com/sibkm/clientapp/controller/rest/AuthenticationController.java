package com.sibkm.clientapp.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sibkm.clientapp.entity.User;
import com.sibkm.clientapp.helper.request.LoginRequest;
import com.sibkm.clientapp.helper.request.RegistrationRequest;
import com.sibkm.clientapp.helper.response.BaseResponse;
import com.sibkm.clientapp.helper.response.LoginResponse;
import com.sibkm.clientapp.service.AuthService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthService authService;
    
    @PostMapping("login")
    public BaseResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        BaseResponse<LoginResponse> response = authService.login(loginRequest);
        log.info("Login response: {}", response);
        return response;
    }

    @PostMapping("registration")
    public User registration(@RequestBody RegistrationRequest registrationRequest) {
        User response = authService.registration(registrationRequest);
        log.info("Registration response: {}", response);
        return response;
    }

    @PostMapping("logout")
    public String logout() {
        authService.logout();
        return "Successfully logout";
    }
}
