package com.mybank.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mybank.entity.User;
import com.mybank.repository.UserRepository;

@Service
public class UserDetailsService
implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository ur;

    public UserDetailsService(UserRepository ur) {
        this.ur = ur;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User u = ur.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found"));

        return new org.springframework.security.core.userdetails.User(

                u.getEmail(),
                u.getPassword(),
                List.of(new SimpleGrantedAuthority("Role_"+u.getRole().name()))
        );
    }
}