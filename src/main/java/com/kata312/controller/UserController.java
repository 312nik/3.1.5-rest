package com.kata312.controller;

import com.kata312.model.User;
import com.kata312.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {

        this.userService = userService;


    }

    @GetMapping("/user")
    public String showPrincipal(Model model, Principal principal) {
        String userMail = principal.getName();
        User user= userService.getUserByEmail(userMail);
        model.addAttribute("userPrincipal",user);

        return "/user";
    }

}
