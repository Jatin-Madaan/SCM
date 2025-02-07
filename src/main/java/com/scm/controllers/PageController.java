package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.scm.entities.User;
import com.scm.forms.UserForm;

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
    public String registerPage(Model model) {
        UserForm userForm = new UserForm();
        // default values bhi set kar sakte hain
        // userForm.setName("Jatin Madaan");
        model.addAttribute("userForm", userForm);
        return "register";
    }

    // processing register form
    @PostMapping("/do-register")
    public String processRegister(@ModelAttribute UserForm userForm) {
        // fetching data from form
        System.out.println("Register form submitted : " + userForm);
        // validating user data
        // saving data to database
        // redirecting to login page
        return "redirect:/register";
    }

}
