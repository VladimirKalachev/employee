package com.employee.service;
import com.employee.models.User;
import com.employee.repo.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class UserService {

    private final UserRepository userRepository;

    private User retUser;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Transactional

    public User addUser(String firstName, String lastName, int companyId, String role) {

        User user = new User(firstName, lastName, companyId, role);
        retUser = userRepository.save(user);
        return retUser;
    }
}







