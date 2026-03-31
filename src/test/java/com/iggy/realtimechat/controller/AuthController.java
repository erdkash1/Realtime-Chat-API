package com.iggy.realtimechat.controller;

import com.iggy.realtimechat.dto.AuthResponse;
import com.iggy.realtimechat.dto.LoginRequest;
import com.iggy.realtimechat.dto.RegisterRequest;
import com.iggy.realtimechat.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public AuthResponse createRegister(@RequestBody RegisterRequest request){ return authService.register(request);}

    @PostMapping("/login")
    public AuthResponse loginRequest(@RequestBody LoginRequest request){ return authService.login(request);}
}