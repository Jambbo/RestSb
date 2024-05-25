package com.example.restsb.web.controller.docs;

import com.example.restsb.web.dto.UserDto;
import com.example.restsb.web.dto.auth.JwtRequest;
import com.example.restsb.web.dto.auth.JwtResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthControllerDoc {

    @Operation(summary = "register user")
    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody UserDto userDto);


    @Operation(summary = "login user")
    @PostMapping("/login")
    JwtResponse login(@RequestBody JwtRequest loginRequest);


    @Operation(summary = "refresh token")
    @PostMapping("/refresh")
    JwtResponse refresh(@RequestBody String refreshToken);

}
