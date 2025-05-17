package com.example.practice_project2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.practice_project2.service.UserService;

@Controller
public class HomeController {

    @Autowired
    private UserService service;

    @GetMapping(value={"/", "/home"})
    public String renderHome(Model model) {
        model.addAttribute("users", service.getAllUsers());
        return "home";
    }

}
