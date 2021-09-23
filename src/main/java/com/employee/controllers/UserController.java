package com.employee.controllers;

import com.employee.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/main")
    public String getData(@RequestParam(value = "firstname", required = false) String firstName,
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
        ArrayList users = userService.userList();
        //System.out.println(users);

        model.addAttribute("userList", users);

       return "/list";
    }

    @PostMapping("/list")
    public String delete(@RequestParam(value = "ID", required = false) Long id){
        if (request.getParameter("delete") != null) {
            userService.deleteUser(id);
        }
        return "redirect:/list";
    }

//    @PostMapping("/main")
//    public String editUser(@RequestParam(value = "ID", required = false) Long id){
//
//
//        userService.editUser(id, getData(String firstname, ));
//        return "/list";
//    }

}
