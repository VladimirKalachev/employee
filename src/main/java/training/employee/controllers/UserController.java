package training.employee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import training.employee.models.Users;
import training.employee.repo.UserRepository;


@Controller
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/")
    @Transactional
    public String userAdd(@RequestParam(value = "firstname", required = false) String firstName,
                          @RequestParam(value = "lastname", required = false)  String lastName,
                          @RequestParam(value = "companyid", required = false) String companyId,
                          @RequestParam(value = "role", required = false) String role,
                          Model model){

        Users users = new Users(firstName, lastName, companyId, role);

        userRepository.save(users);

       // userRepository.save(users);
        return "/message";
    }

}
