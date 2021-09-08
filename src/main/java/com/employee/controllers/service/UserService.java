package com.employee.controllers.service;

import com.employee.models.Users;
import com.employee.repo.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class UserService {
    protected final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/main")
    @Transactional
    public String userAdd(@RequestParam(value = "firstname", required = false) String firstName,
                          @RequestParam(value = "lastname", required = false) String lastName,
                          @RequestParam(value = "companyid", required = false) int companyId,
                          @RequestParam(value = "role", required = false) String role,
                          Model model) {

        Users users = new Users(firstName, lastName, companyId, role);

        userRepository.save(users);

        return "/message";
    }
}
