package com.example.restsb.web.controller;

import com.example.restsb.domain.User;
import com.example.restsb.service.AuthService;
import com.example.restsb.service.UserService;
import com.example.restsb.web.dto.UserDto;
import com.example.restsb.web.dto.auth.JwtRequest;
import com.example.restsb.web.dto.auth.JwtResponse;
import com.example.restsb.web.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto userDto){
        User user = userMapper.toEntity(userDto);
        User createdUser = userService.create(user);
        return new ResponseEntity<>("You successfully registered!",HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody JwtRequest loginRequest){
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken){
        return authService.refresh(refreshToken);
    }




}
