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


    @GetMapping("/profile/{username}")
    public String profile(@PathVariable String username, Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        User user = userService.getUsername(username);
        if (user != null && userDetails.getUsername().equals(user.getUsername())) {
            model.addAttribute("user", user);
            return "profile";
        } else {
            return "404";
        }
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


    @GetMapping("/edit/{username}")
    public String edit(@PathVariable String username, Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        User user = userService.getUsername(username);
        if (user != null && userDetails.getUsername().equals(user.getUsername())) {
            model.addAttribute("user", user);
            return "edit";
        } else {
            return "404";
        }
    }

    @PostMapping("/updateUser")
    public String update(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/delete/{username}")
    public String loadEmpSave(@PathVariable String username, HttpSession session){
        User user = userService.getUsername(username);
        if(user != null){
            userService.deleteUser(username); // Lakukan operasi penghapusan pengguna
            session.setAttribute("msg", "Delete successful");
        } else {
            session.setAttribute("msg", "Something wrong on server");
        }
        return "redirect:/";
    }
}

