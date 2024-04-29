package com.example.restsb.service.impl;

import com.example.restsb.domain.User;
import com.example.restsb.service.AuthService;
import com.example.restsb.service.UserService;
import com.example.restsb.web.dto.auth.JwtRequest;
import com.example.restsb.web.dto.auth.JwtResponse;
import com.example.restsb.web.secutiry.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        String username = loginRequest.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,loginRequest.getPassword()));
            User user = userService.getByUsername(username);
            Long userId = user.getId();
            return JwtResponse.builder()
                    .id(userId)
                    .username(username)
                    .accessToken(jwtTokenProvider.createAccessToken(userId,username,user.getRoles()))
                    .refreshToken(jwtTokenProvider.createRefreshToken(userId,username))
                    .build();
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return jwtTokenProvider.refreshUserTokens(refreshToken);
    }
}
