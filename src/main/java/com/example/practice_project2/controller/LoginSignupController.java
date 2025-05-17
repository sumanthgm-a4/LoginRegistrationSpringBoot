package com.example.practice_project2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.practice_project2.model.AppUser;
import com.example.practice_project2.repository.UserRepo;

@Controller
public class LoginSignupController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo repo;

    @GetMapping(value="/login")
    public String renderLogin() {
        return "login";
    }

    @GetMapping(value="/signup")
    public String renderSignup() {
        return "signup";
    }

    @PostMapping(value="/signup")
    public String createUser(@ModelAttribute AppUser user, 
                                @RequestParam("confirmPassword") String cpword, 
                                Model model) {
        
        System.out.println("Username: " + user.getUsername()
                            + "\nPassword: " + user.getPassword()
                            + "\nConfirm Password: " + cpword);

        // Spring Boot does this automatically
        // AppUser user = new AppUser();
        // user.setUsername(request.getParameter("username"));  // from the input field
        // user.setPassword(request.getParameter("password"));  // from the input field

        if (!user.getPassword().equals(cpword)) {
            model.addAttribute("error", "Passwords do not match!");
            return "signup";
        }
    
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
    
        return "redirect:/login";
    }

}
