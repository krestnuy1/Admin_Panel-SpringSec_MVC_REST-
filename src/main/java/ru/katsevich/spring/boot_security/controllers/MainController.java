package ru.katsevich.spring.boot_security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.katsevich.spring.boot_security.entities.User;
import ru.katsevich.spring.boot_security.services.RoleService;
import ru.katsevich.spring.boot_security.services.UserService;
import java.security.Principal;



@Controller
public class MainController {

    private final UserService userService;
    private final RoleService roleService;
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
    public String userPage(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("thisUser", user);
        return "user";
    }


    @GetMapping("/admin")
    public String adminPage(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("thisUser", user);
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.findAll());
        return "adminPage";
    }

}
