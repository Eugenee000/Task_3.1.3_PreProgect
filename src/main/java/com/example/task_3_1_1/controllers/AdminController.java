package com.example.task_3_1_1.controllers;

import com.example.task_3_1_1.model.Role;
import com.example.task_3_1_1.model.User;
import com.example.task_3_1_1.service.RoleService;
import com.example.task_3_1_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("admin", ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        model.addAttribute("users", userService.getAllUsers());
        return "admin/show_admin_info_about_users";
    }

    @GetMapping("/{id}/edit")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/edit";
    }

    @PostMapping("/{id}")
    public String upDate(@ModelAttribute("user") User user,
                         @PathVariable("id") Long id,
                         @RequestParam(value = "role", required = false) String[] role) {
        List<Role> roleSet = new ArrayList<>();
        if (role != null) {
            for (String roles : role) {
                roleSet.add(roleService.getRoleByName(roles));
            }
            user.setRoles(roleSet);
        }
        userService.updateUser(user, id);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("admin", user);
        model.addAttribute("user", new User());
        return "admin/new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam("role") String[] role) {
        List<Role> roleSet = new ArrayList<>();
        if (role != null) {
            for (String roles : role) {
                roleSet.add(roleService.getRoleByName(roles));
            }
            user.setRoles(roleSet);
        }
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/info")
    public String adminInfo(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("admin", userService.getUserByEmail(email));
        return "admin/show_admin";
    }
}
