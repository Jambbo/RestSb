package com.example.restsb.web.controller;

import com.example.restsb.domain.User;
import com.example.restsb.service.AuthService;
import com.example.restsb.service.UserService;
import com.example.restsb.web.controller.docs.AuthControllerDoc;
import com.example.restsb.web.dto.UserDto;
import com.example.restsb.web.dto.auth.JwtRequest;
import com.example.restsb.web.dto.auth.JwtResponse;
import com.example.restsb.web.mappers.UserMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "Auth Controller",description = "Auth API")
public class AuthController implements AuthControllerDoc {
    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthService authService;

    @Override
    public ResponseEntity<?> register(@RequestBody UserDto userDto){
        User user = userMapper.toEntity(userDto);
        User createdUser = userService.create(user);
        return new ResponseEntity<>("You successfully registered!",HttpStatus.CREATED);
    }

    @Override
    public JwtResponse login(@RequestBody JwtRequest loginRequest){
        return authService.login(loginRequest);
    }


    @Override
    public JwtResponse refresh(@RequestBody String refreshToken){
        return authService.refresh(refreshToken);
    }




}
