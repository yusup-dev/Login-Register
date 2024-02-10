package com.latihan.controller;

import com.latihan.dto.UserDto;
import com.latihan.entity.User;
import com.latihan.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    private final UserDetailsService userDetailsService;
    private final UserService userService;

    @Autowired
    public UserController(UserDetailsService userDetailsService, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("userdetail", userDetails);
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model, UserDto userDto) {
        model.addAttribute("user", userDto);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model, UserDto userDto) {
        model.addAttribute("user", userDto);
        return "register";
    }

    @PostMapping("/register")
    public String registerSave(@ModelAttribute("user") UserDto userDto, Model model) {
        User user = userService.findByUsername(userDto.getUsername());
        if (user != null) {
            model.addAttribute("userexist", user);
            return "register";
        }
        userService.save(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable int id, Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        User user = userService.getUserId(id);
        if (user != null && userDetails.getUsername().equals(user.getUsername())) {
            model.addAttribute("user", user);
            return "profile";
        } else {
            return "404";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        User user = userService.getUserId(id);
        if (user != null && userDetails.getUsername().equals(user.getUsername())) {
            model.addAttribute("user", user);
            return "edit";
        } else {
            return "404";
        }
    }

    @PostMapping("/updateUser")
    public String update(@ModelAttribute User user, HttpSession session) {
        User updateUser = userService.save(user);
        if (updateUser != null) {
            System.out.println("save success");
            session.setAttribute("msg", "Update Successfully");
        } else {
            System.out.println("something wrong on server");
            session.setAttribute("msg", "something wrong on server");
        }
        return "redirect:/profile/{id}";
    }
}

