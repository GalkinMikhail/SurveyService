package com.example.fabriquetesttask.security;

import com.example.fabriquetesttask.model.Roles;
import com.example.fabriquetesttask.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }
    public static SecurityUser create(User user){
        return new SecurityUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))
        );
    }
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Roles> userRoles){
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());
    }
}
