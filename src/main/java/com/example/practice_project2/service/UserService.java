package com.example.practice_project2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.practice_project2.model.AppUser;
import com.example.practice_project2.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public List<AppUser> getAllUsers() {
        return repo.findAll();
    }

}
