package training.employee.controllers;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import training.employee.models.Users;


@Controller
public class UserController {

    @PostMapping("/")
    public String userAdd(@RequestParam(value = "firstname", required = false) String firstName,
                          @RequestParam(value = "lastname", required = false)  String lastName,
                          @RequestParam(value = "companyid", required = false) String companyId,
                          @RequestParam(value = "role", required = false) String role,
                          Model model){

        try(Session session = HibernateUtil.getSession()){
            session.beginTransaction();
            Users users = new Users(firstName, lastName, companyId, role);
            session.save(users);
            session.getTransaction().commit();
        } catch (Throwable cause) {
            cause.printStackTrace();
        }
       // userRepository.save(users);
        return "/message";
    }

}
