package com.employee.controllers;

import com.employee.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/main")
    public String createUser(@RequestParam(value = "firstname", required = false) String firstName,
                         @RequestParam(value = "lastname", required = false)  String lastName,
                         @RequestParam(value = "companyid", required = false) int companyId,
                         @RequestParam(value = "role", required = false) String role,
                         Model model) {

        Long userID =  userService.addUser(firstName, lastName, companyId, role).getId();

        model.addAttribute("userID", userID);

        return "/main";
    }

    @GetMapping("/list")
    public String listData(Model model) {
        Iterable users  = userService.userList();
        model.addAttribute("userList", users);
        return "/list";
    }

    @GetMapping("/list/{id}")
    public String deleteUser(@PathVariable(value = "id", required = false) Long id, Model model) {
        System.out.println("ID is " + id);
        model.addAttribute("user", userService.showUser(id));

        return "/edit";
    }





}
