package com.employee.service;

import com.employee.models.User;
import com.employee.repo.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Transactional
    public User addUser(String firstName, String lastName, int companyId, String role) {

        User user = new User(firstName, lastName, companyId, role);

        return userRepository.save(user);
    }

//    @Transactional
//    public User editUser(User) {
//
//    }

    @Transactional
    public ArrayList<User> userList(){
        Iterable<User> users = userRepository.findAll();
        ArrayList<User> userList = new ArrayList<>();
        users.forEach(userList::add);
        return userList;
    }







}







