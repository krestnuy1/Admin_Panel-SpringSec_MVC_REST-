package ru.katsevich.spring.boot_security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.katsevich.spring.boot_security.entities.Role;
import ru.katsevich.spring.boot_security.entities.User;
import ru.katsevich.spring.boot_security.services.RoleService;
import ru.katsevich.spring.boot_security.services.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {


    private UserService userService;
    private RoleService roleService;
    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }



    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("userID") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

//    @PostMapping("/update")
//    public String saveUserByAdmin(@ModelAttribute("editinguser") User user,
//                                  BindingResult result,
//                                  @RequestParam("selectedRoles") List<Long> selectedRoleIds,
//                                  Model model) {
//        if (result.hasErrors()) {
//            model.addAttribute("allRoles", roleService.findAll());
//            return "addOrUpdateUser";
//        }
//        Set<Role> selectedRoles = new HashSet<>();
//        for (Long roleId : selectedRoleIds) {
//            Role role = roleService.findById(roleId).orElse(null);
//            if (role != null) {
//                selectedRoles.add(role);
//            }
//        }
//        User existingUser = userService.findByUsername(user.getUsername());
//        if (existingUser == null) {
//            existingUser.setFirstname(user.getFirstname());
//            existingUser.setLastname(user.getLastname());
//            existingUser.setAge(user.getAge());
//            existingUser.setEmail(user.getEmail());
//            user.setRoles(selectedRoles);
//            userService.save(existingUser);
//        }
//
//        user.setRoles(selectedRoles);
//        userService.save(user);
//
//        return "redirect:/admin";
//    }

//    @PostMapping("/update")
//    public String updateUser(@ModelAttribute("editinguser") User user,
//                             @RequestParam("selectedRoles") List<Long> selectedRoleIds) {
//        Set<Role> selectedRoles = new HashSet<>();
//        for (Long roleId : selectedRoleIds) {
//            Role role = roleRepository.findById(roleId).orElse(null);
//            if (role != null) {
//                selectedRoles.add(role);
//            }
//        }
//        User existingUser = userService.findByUsername(user.getUsername());
//
//        existingUser.setFirstname(user.getFirstname());
//        existingUser.setLastname(user.getLastname());
//        existingUser.setAge(user.getAge());
//        existingUser.setEmail(user.getEmail());
//        user.setRoles(selectedRoles);
//        userService.save(existingUser);
//
//
//        return "redirect:/admin";
//    }

    @PostMapping("/update")
    public String saveUserByAdmin(@ModelAttribute("user") User user,
                                  BindingResult result,
                                  @RequestParam("selectedRoles") List<Long> selectedRoleIds,
                                  Model model) {
        if (result.hasErrors()) {
            model.addAttribute("allRoles", roleService.findAll());
            return "addOrUpdateUser";
        }
        Set<Role> selectedRoles = new HashSet<>();
        for (Long roleId : selectedRoleIds) {
            Role role = roleService.findById(roleId).orElse(null);
            if (role != null) {
                selectedRoles.add(role);
            }
        }

        user.setRoles(selectedRoles);
        userService.save(user);

        return "redirect:/admin";
    }
}
