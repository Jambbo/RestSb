package com.example.restsb.web.dto.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {

    private Long id;
    private String username;
    private String accessToken;
    private String refreshToken;

}
