package com.example.mockbasev2.service.impl;

import com.example.mockbasev2.common.constant.HttpStatusConstant;
import com.example.mockbasev2.dto.auth.LoginDTO;
import com.example.mockbasev2.dto.auth.RegisterDTO;
import com.example.mockbasev2.entity.Role;
import com.example.mockbasev2.entity.User;
import com.example.mockbasev2.exception.CustomAPIException;
import com.example.mockbasev2.repository.RoleRepository;
import com.example.mockbasev2.repository.UserRepository;
import com.example.mockbasev2.security.JwtTokenProvider;
import com.example.mockbasev2.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private JwtTokenProvider jwtTokenProvider;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDTO loginDTO) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(RegisterDTO registerDTO) {

        // add check for username exists in database
        if(userRepository.existsByUsername(registerDTO.getUsername())){
            throw new CustomAPIException(HttpStatusConstant.NULL_POINTER_OR_BAD_REQUEST_CODE, "Username is already exists!.");
        }


        User user = new User();
        user.setName(registerDTO.getName());
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

//        Set<Role> roles = new HashSet<>();
//        Role userRole = roleRepository.findByName("ROLE_USER").get();
//        roles.add(userRole);
//        user.setRoles(roles);

        userRepository.save(user);

        return "User registered successfully!.";
    }

}
