package com.example.mockbasev2.controller.auth;

import com.example.mockbasev2.dto.auth.LoginDTO;
import com.example.mockbasev2.dto.response.JwtAuthResponse;
import com.example.mockbasev2.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    // Build Login REST API
//    @PostMapping(value = {"/login", "/signin"})
//    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
//
//        String token = authService.login(loginDTO);
//
//        return ResponseEntity.ok(token);
//    }
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDTO loginDto){
        String token = authService.login(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }
}
