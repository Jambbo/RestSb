package com.example.restsb.service;

import com.example.restsb.web.dto.auth.JwtRequest;
import com.example.restsb.web.dto.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);
    JwtResponse refresh(String refreshToken);

}
