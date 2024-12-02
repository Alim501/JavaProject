package com.example.demoAuth.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demoAuth.entity.User;
import com.example.demoAuth.repository.UserRepository;

import java.util.Collections;

@Service
public class CustomUserDetails implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetails(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Retrieve the user from the database
        User user = userRepository.findByEmail(email);

        if (user != null) {
            // Convert the role to a SimpleGrantedAuthority and create a UserDetails object
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName());
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    Collections.singletonList(authority) // Role as a collection of GrantedAuthority
            );
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}
