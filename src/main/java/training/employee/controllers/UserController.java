package training.employee.controllers;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import training.employee.models.Users;
import training.employee.repo.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/")
    public String userAdd(@RequestParam String first_name,
                          @RequestParam String last_name, @RequestParam int company_id,
                          @RequestParam String role, Model model){
        Users users = new Users(first_name, last_name, company_id, role);
        userRepository.save(users);
        return "/message";
    }

}
