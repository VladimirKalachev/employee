package training.employee.controllers;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import training.employee.models.Users;


@Controller
public class UserController {

    @PostMapping("/")
    public String userAdd(@RequestParam String first_name,
                          @RequestParam String last_name,
                          @RequestParam int company_id,
                          @RequestParam String role,
                          Model model){

        try(Session session = HibernateUtil.getSession()){
            session.beginTransaction();
            Users users = new Users(first_name, last_name, company_id, role);
            session.save(users);
            session.getTransaction().commit();
        } catch (Throwable cause) {
            cause.printStackTrace();
        }
       // userRepository.save(users);
        return "/message";
    }

}
