package com.example.restsb.service;

import com.example.restsb.domain.User;

public interface UserService {

    User getByUsername(String username);

    User getById(Long id);
    User create(User user);

}
