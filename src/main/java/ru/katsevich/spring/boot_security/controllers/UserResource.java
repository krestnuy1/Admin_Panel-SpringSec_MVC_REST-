package ru.katsevich.spring.boot_security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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


import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Set;

@RestController
@RequestMapping("/admin/api/user")
public class UserResource {
    private UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable Long userId) {
        User getUser = userService.getById(userId);
        User user = new User();
        user.setId(getUser.getId());
        System.out.println(getUser.getRoles().toString());
        user.setUsername(getUser.getUsername());
        user.setFirstname(getUser.getFirstname());
        user.setLastname(getUser.getLastname());
        user.setAge(getUser.getAge());
        user.setEmail(getUser.getEmail());
//        Set<Role> currentRoles = getUser.getRoles();
        user.setRoles(getUser.getRoles());
        return user;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        User existingUser = userService.findByUsername(user.getUsername());

        existingUser.setFirstname(user.getFirstname());
        existingUser.setLastname(user.getLastname());
        existingUser.setAge(user.getAge());
        existingUser.setEmail(user.getEmail());
        existingUser.setRoles(user.getRoles());

        userService.save(existingUser);

        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

}
