package com.employee.controllers;

import com.employee.repo.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.employee.models.Users;


@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/main")
    @Transactional
    public String userAdd(@RequestParam(value = "firstname", required = false) String firstName,
                          @RequestParam(value = "lastname", required = false)  String lastName,
                          @RequestParam(value = "companyid", required = false) int companyId,
                          @RequestParam(value = "role", required = false) String role,
                          Model model){

        Users users = new Users(firstName, lastName, companyId, role);

        userRepository.save(users);

        return "/message";
    }
}
