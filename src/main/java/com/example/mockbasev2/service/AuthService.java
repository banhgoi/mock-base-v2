package com.example.mockbasev2.service;

import com.example.mockbasev2.dto.auth.LoginDTO;
import com.example.mockbasev2.dto.auth.RegisterDTO;

public interface AuthService {
    String login(LoginDTO loginDTO);

    String register(RegisterDTO registerDTO);
}
