package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {

    @GetMapping("/profile/{username}")
    public String showProfile(@PathVariable String username, Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        username = user.getUsername();
        model.addAttribute("user", username);
        return "profile";
    }
}
