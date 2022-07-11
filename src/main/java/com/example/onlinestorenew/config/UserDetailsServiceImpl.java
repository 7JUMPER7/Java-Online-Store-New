package com.example.onlinestorenew.config;

import java.util.HashSet;
import java.util.Set;

import com.example.onlinestorenew.models.UserEntity;
import com.example.onlinestorenew.services.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserService userService = new UserService();
        UserEntity user = userService.findByEmail(username);

        Set < GrantedAuthority > grantedAuthorities = new HashSet < > ();
        if(user != null) {
            if(user.getRole().equals("ADMIN")) {
                grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
            }
            grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                grantedAuthorities);
    }
}