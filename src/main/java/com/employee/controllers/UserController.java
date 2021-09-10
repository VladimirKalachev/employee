package com.employee.controllers;

import com.employee.models.Users;
import com.employee.repo.UserRepository;
import com.employee.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    /* работа с интерфейсом, который используется для обращения к БД,
        должно перейти в сервис*/
/*
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
*/
    //обработка запроса HTTP Post. остается в контроллере
    @PostMapping("/main")

    //транзакция БД,  должно перейти в UserService
    //@Transactional

    /* получение из формы переменных, которые станут значениями полей users,
    тут работа с HTTP - следовательно должно остаться в контроллере */
    /*
    public String userAdd(@RequestParam(value = "firstname", required = false) String firstName,
                          @RequestParam(value = "lastname", required = false)  String lastName,
                          @RequestParam(value = "companyid", required = false) int companyId,
                          @RequestParam(value = "role", required = false) String role,
                          Model model){
*/
        /*создание объекта класса User с полями, полученными по запросу из формы
        * это уже должно быть в сервисе */
        //Users users = new Users(firstName, lastName, companyId, role);

     UserService userService = new UserService();


    //добавление записи в БД, должно быть в UserService
      // UserRepository.save(UserService);

        //обработка запроса HTTP Post. остается в контроллере
        @PostMapping"/message";
    }
}
