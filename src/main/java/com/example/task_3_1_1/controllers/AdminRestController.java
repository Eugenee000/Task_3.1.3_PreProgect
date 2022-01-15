package com.example.task_3_1_1.controllers;

import com.example.task_3_1_1.model.Role;
import com.example.task_3_1_1.model.User;
import com.example.task_3_1_1.service.RoleService;
import com.example.task_3_1_1.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok().body(roleService.getAllRoles());
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserPages(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
//        if (id == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok().body(userService.getUserById(id));
        User user = userService.getUserById(id);
        return (user != null)
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        userService.addUser(user);
        System.out.println(user);
        return ResponseEntity.ok().body(user);
    }

//    @PatchMapping("/update/{id}")
//    public ResponseEntity<User> updateUser(@RequestBody User user,
//                                           @PathVariable("id") Long id) {
//        userService.updateUser(user, id);
//        return ResponseEntity.ok().body(user);
//    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Long id) {
        userService.updateUser(getUsersRole(user), id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    private User getUsersRole(User user) {
        List<Role> roles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            roles.add(roleService.getRoleByName(role.getName()));
        }
        user.setRoles(roles);
        return user;
    }
}
