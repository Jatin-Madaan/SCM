package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class PageController {

    @GetMapping("/home")
    public String getHome(Model model) {
        model.addAttribute("name", "Smart Contact Manager");
        model.addAttribute("GitHubRepo", "https://github.com/Jatin-Madaan/SCM");
        return "home";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }
    
    @GetMapping("/services")
    public String servicesPage() {
        return "services";
    }

    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }
    
}
