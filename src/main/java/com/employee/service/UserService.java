package com.employee.service;

import com.employee.models.User;
import com.employee.repo.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
//    public ArrayList<User> userList(){
//        Iterable<User> users = userRepository.findAll();
//        ArrayList<User> usersList = new ArrayList<>();
//        users.forEach(usersList::add);
//        return usersList;
//    }

    @Transactional
    public Iterable<User> userList(){
        Iterable<User> users = userRepository.findAll();
        return users;
    }

    @Transactional
    public void deleteUser(long id){
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }

    @Transactional
    public User showUser(long id){
        User user = userRepository.findById(id).orElseThrow();
        return user;
    }






}







