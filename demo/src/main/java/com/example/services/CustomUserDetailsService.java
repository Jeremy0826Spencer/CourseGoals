package com.example.services;

import com.example.daos.UserDAO;
import com.example.models.User;
import com.example.models.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserDAO userDAO;

    @Autowired
    public CustomUserDetailsService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userDAO.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User with this username or email does not exist."));
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role -> new SimpleGrantedAuthority(role.getName())))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(
                usernameOrEmail,
                user.getPassword(),
                authorities
        );
    }
}
