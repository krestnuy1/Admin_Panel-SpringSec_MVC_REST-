package ru.katsevich.spring.boot_security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.katsevich.spring.boot_security.entities.User;
import ru.katsevich.spring.boot_security.repository.RoleRepository;
import ru.katsevich.spring.boot_security.repository.UserRepository;
import ru.katsevich.spring.boot_security.services.RoleService;
import ru.katsevich.spring.boot_security.services.UserService;

import java.security.Principal;
import java.util.List;



@Controller
public class MainController {

    private UserService userService;

    private RoleService roleService;
    @Autowired
    public MainController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/user")
    public String userPage(Model model, Principal principal, Authentication authentication) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("thisUser", user);
        return "user";
    }


    @GetMapping("/admin")
    public String adminPage(Model model, Principal principal) {
        List<User> users = userService.findAll();
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("userRoles", user.getRolesasstring());
        model.addAttribute("thisUser", user);
        model.addAttribute("allUsers", users);
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.findAll());
        return "adminPage";
    }

}
