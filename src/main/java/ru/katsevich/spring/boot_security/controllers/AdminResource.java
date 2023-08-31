package ru.katsevich.spring.boot_security.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.katsevich.spring.boot_security.entities.Role;
import ru.katsevich.spring.boot_security.entities.User;
import ru.katsevich.spring.boot_security.services.RoleService;
import ru.katsevich.spring.boot_security.services.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/admin")
public class AdminResource {

    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public AdminResource(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @DeleteMapping("/deleteUser/{userID}")
    public void deleteUser(@PathVariable Long userID) {
        userService.deleteById(userID);
    }


    @PostMapping("/update")
    public void UpdateUser(@RequestBody User user) {
        User existingUser = userService.findByUsername(user.getUsername());
        if (existingUser != null) {
            user.setId(existingUser.getId());
            userService.save(user);
        } else {
            userService.save(user);
        }
    }

    @PostMapping("/save")
    public void saveUser(@RequestBody User user) {
        Set<Role> roles = user.getRoles();
        Set<Role> existingRoles = new HashSet<>();
        for (Role role : roles) {
            existingRoles.add(roleService.findById(role.getId()).orElse(null));
        }
        user.setRoles(existingRoles);
        userService.save(user);
    }

    @GetMapping("/user/{userId}")
    public User findById(@PathVariable Long userId) {
        User getUser = userService.getById(userId);
        User user = new User();
        user.setId(getUser.getId());
        user.setUsername(getUser.getUsername());
        user.setFirstname(getUser.getFirstname());
        user.setLastname(getUser.getLastname());
        user.setAge(getUser.getAge());
        user.setEmail(getUser.getEmail());
//        Set<Role> currentRoles = getUser.getRoles();
        user.setRoles(getUser.getRoles());
        return user;
    }
}
