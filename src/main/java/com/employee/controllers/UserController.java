package com.employee.controllers;

import com.employee.controllers.service.UserService;
import com.employee.repo.UserRepository;
import org.springframework.stereotype.Controller;


@Controller
public class UserController extends UserService {

    public UserController(UserRepository userRepository) {
        super(userRepository);
    }

}
