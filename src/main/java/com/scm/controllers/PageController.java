package com.scm.controllers;



import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import ch.qos.logback.classic.Logger;
import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    Logger logger = (Logger)LoggerFactory.getLogger(PageController.class);

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
    public String processRegister(@ModelAttribute UserForm userForm, HttpSession session) {
        // fetching data from form
        logger.info("Register form submitted : " + userForm);
        // validating user data
        if (userService.isUserExistsByEmail(userForm.getEmail())) {
            logger.error("User already exists with email: " + userForm.getEmail());
            return "redirect:/register?error";
        }
        // saving data to database
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        userService.saveUser(user);

        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();    
        session.setAttribute("message", message);
        // redirecting to login page
        return "redirect:/register";
    }

}
