package ru.orlov.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.orlov.security.model.User;
import ru.orlov.security.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping()
    public String singleUser(Model model, Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
//        System.out.println(user.toString());
        model.addAttribute("singleUser", user);
        return "singleUser";
    }
}
