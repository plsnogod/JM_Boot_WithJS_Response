package com.plsnogod.jmboot.controllers;

import com.plsnogod.jmboot.model.User;
import com.plsnogod.jmboot.service.RoleService;
import com.plsnogod.jmboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("")
public class AdminController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user")
    public String user(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByName(principal.getName()));
        return "page_user";
    }

    @GetMapping("/admin")
    public String showAllUsers(ModelMap model) {
        List<User> list_user = userService.showAllUsers();
        model.addAttribute("all_roles", roleService.list_roles());
        model.addAttribute("all_us", list_user);
        model.addAttribute("new_user", new User());
        return "table_users";
    }
}