package com.employee.controllers;
import com.employee.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

       Long userID =  userService.addUser(firstName, lastName, companyId, role);

       System.out.println("usercontroller" + userID);



        return "/message";

    }
}
