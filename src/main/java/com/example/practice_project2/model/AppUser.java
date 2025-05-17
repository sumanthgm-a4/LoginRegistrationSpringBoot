package com.example.practice_project2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="app_user_2")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate the ID with an auto-incrementing strategy
    private Long id;

    private String username;
    private String password;

}
