package com.shourov.springbootsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "this is home";
    }

    @GetMapping("/login")
    public String login() {
        return "this is login";
    }

    @GetMapping("/register")
    public String register() {
        return "this is register";
    }
}
