package com.kata312.controller;



import com.kata312.model.Role;
import com.kata312.model.User;

import com.kata312.service.RoleServiceImpl;
import com.kata312.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import java.security.Principal;

import java.util.ArrayList;
import java.util.List;


@Controller

public class AdminController {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;


    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {

        this.userService = userService;


        this.roleService = roleService;
    }



    @GetMapping("/admin")
    public String showAllUsers(Model model, Principal principal) {
        String userMail = principal.getName();
        User user= userService.getUserByEmail(userMail);
        List<User> users = userService.getAllUsers();
        User newUser=new User();
        model.addAttribute("userPrincipal",user);
        model.addAttribute("users", users);
        model.addAttribute("newUser",newUser);
        return "/admin";



    }




    @PostMapping("admin/new")
    public String createUser(@ModelAttribute("newUser")  User user,@RequestParam(value = "selectRoles") String[] selectRole,RedirectAttributes redirectAttributes) {


        try {

            User userNew= userService.getUserByEmail(user.getEmail());
            if (userNew != null) {
                redirectAttributes.addFlashAttribute("message",
                        "A user with such an email already exists!");

                return "redirect:/admin";
            }
        } catch(Exception ignore){}


        List <Role> userRole =  new ArrayList<>();
        for (String role: selectRole ) {
            userRole.add(roleService.getRoleByName(role));
        }
        user.setRoles(userRole);

        System.out.println(userRole);

            userService.addUser(user);
            return "redirect:/admin";


    }

    @DeleteMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id)  {

        userService.removeUserById(id);
        return "redirect:/admin";

    }



    @PatchMapping("/admin/edit")
    public String update( User user, RedirectAttributes redirectAttributes) {



        if (userService.getUserByEmail(user.getUsername()) != null &&
                !userService.getUserByEmail(user.getUsername()).getId().equals(user.getId())) {
            redirectAttributes.addFlashAttribute("message",
                    "A user with such an email already exists!");
        }

        userService.updateUser(user);
        return "redirect:/admin";

    }

}




