package ru.orlov.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.orlov.security.model.AppUserRoles;
import ru.orlov.security.model.Role;
import ru.orlov.security.model.User;
import ru.orlov.security.service.RoleService;
import ru.orlov.security.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostConstruct
    void init() {
        Arrays.stream(AppUserRoles.values())
                .map(x -> x.toString())
                .forEach(x -> roleService.addRole(new Role(x)));

        User startUser = new User("admin", "admin", "admin", "admin");
        startUser.addRoleToUser(roleService.findByName("ROLE_ADMIN"));
        userService.addUser(startUser);

    }


    @GetMapping()
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/allUsers";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("singleUser", userService.getByIdUser(id));
        return "singleUser";
    }

    @GetMapping("/add")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        List<Role> allRoles = (List<Role>) roleService.getAllRoles();
        model.addAttribute("allRoles", allRoles);
        return "admin/add";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("updateUser", userService.getByIdUser(id));
        List<Role> allRoles = (List<Role>) roleService.getAllRoles();
        model.addAttribute("allRoles", allRoles);
        return "admin/editUser";
    }

    @PutMapping()
    public String doEdit(@ModelAttribute("updateUser") User newUser) {
        userService.editUser(newUser);
        return "redirect:/admin";
    }
}
