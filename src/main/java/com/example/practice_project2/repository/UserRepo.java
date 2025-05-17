package com.example.practice_project2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.practice_project2.model.AppUser;

@Repository
public interface UserRepo extends JpaRepository<AppUser, Long> {
    
    Optional<AppUser> findByUsername(String username);

}
