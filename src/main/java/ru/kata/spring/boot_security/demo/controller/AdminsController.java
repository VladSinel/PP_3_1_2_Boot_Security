package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImp;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class AdminsController {

    private final UserServiceImp userService;
    private final RoleServiceImp roleService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    AdminsController(UserServiceImp userService, RoleServiceImp roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin/users")
    public String listUsers(ModelMap model, User user) {
        List<User> listUsers = userService.findAll();
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("newUser", user);
        return "users";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "/edit";
    }

    @PostMapping("/user")
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam(value = "role") ArrayList<Long> roles) {
        Set<Role> roleArrayList = roleService.getRoles(roles);
        user.setRoles(roleArrayList);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/admin/users";
    }

    @PatchMapping("/admin")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "role") ArrayList<Long> roles) {
        Set<Role> roleArrayList = roleService.getRoles(roles);
        user.setRoles(roleArrayList);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/remove/{id}")
    public String removeUser(@PathVariable long id) {
        userService.deleteById (id);
        return "redirect:/admin/users";
    }

}
