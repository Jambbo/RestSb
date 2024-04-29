package com.example.restsb.web.secutiry;

import com.example.restsb.domain.Role;
import com.example.restsb.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class JwtEntityFactory {

    public static JwtEntity create(User user){
        return new JwtEntity(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getPassword(),
                mapToGratedAuthorities(new ArrayList<>(user.getRoles()))
        );
    }

    private static Collection<? extends GrantedAuthority> mapToGratedAuthorities(ArrayList<Role> roles) {
        return roles.stream()
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
