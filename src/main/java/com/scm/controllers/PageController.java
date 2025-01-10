package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class PageController {

    @GetMapping("/home")
    public String getHome(Model model) {
        model.addAttribute("name", "Smart Contact Manager");
        model.addAttribute("GitHubRepo", "https://github.com/Jatin-Madaan/SCM");
        return "home";
    }

    @GetMapping("/about")
    public String aboutPage(Model model, @RequestParam Boolean isLogin) {
        model.addAttribute("isLogin", isLogin);
        return "about";
    }
    
    @GetMapping("/services")
    public String servicesPage() {
        return "services";
    }
    
}
