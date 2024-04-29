package com.example.restsb.web.dto.auth;

import lombok.Data;

@Data
public class JwtRequest {

    public String username;
    public String password;

}
