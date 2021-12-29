package com.example.task_3_1_1.controllers;

import com.example.task_3_1_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user")
    public String index(Model modelMap) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        modelMap.addAttribute("user", userService.getUserByEmail(email));
        return "user/show_user";
    }
}