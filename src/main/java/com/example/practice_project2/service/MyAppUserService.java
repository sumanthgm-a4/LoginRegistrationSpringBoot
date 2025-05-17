package com.example.practice_project2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.practice_project2.model.AppUser;
import com.example.practice_project2.repository.UserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MyAppUserService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = repo.findByUsername(username);
        if (user.isPresent()) {
            var userObj = user.get();
            System.out.println(userObj.getUsername());
            System.out.println(userObj.getPassword());
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
