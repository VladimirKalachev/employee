package com.employee.service;

import com.employee.models.User;
import com.employee.repo.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

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

    @Transactional
    public Iterable<User> userList(){

        Iterable<User> users = userRepository.findAll();

        return users;
    }

    @Transactional
    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }

    @Transactional
    public ArrayList editUser(Long id){

        Optional<User> user = userRepository.findById(id);
        ArrayList<User> result = new ArrayList<>();
        user.ifPresent(result::add);
        return result;

    }
    @Transactional
    public User updateUser(Long id, String firstName, String lastName, int companyId, String role) {

        User user = userRepository.findById(id).orElseThrow();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setCompanyId(companyId);
        user.setRole(role);

        return userRepository.save(user);


    }
}







