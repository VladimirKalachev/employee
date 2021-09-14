package com.employee.service;
import com.employee.models.Users;
import com.employee.repo.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class UserService {

    private final UserRepository userRepository;
    private Users users;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }
    @Transactional
    public void addUser(Users users){
        this.users = users;
        userRepository.save(users);
    }

}


