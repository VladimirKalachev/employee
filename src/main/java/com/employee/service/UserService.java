package com.employee.service;

import com.employee.models.User;
import com.employee.repo.UserRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

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
    public Iterable<User> userList() {

        Iterable<User> users = userRepository.findAll();

        return users;
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }

    @Transactional
    public User editUser(Long id) {

        User user = userRepository.findById(id).orElseThrow();

        return user;

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

    @Transactional
    public void csvToUsers(MultipartFile file) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
/*
            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(User.class);
            String[] header = {"id", "firstName", "lastName", "companyId", "role"};
            strategy.setColumnMapping(header);
*/
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                   // .withMappingStrategy(strategy)
                    .withType(User.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List users = csvToBean.parse();

            userRepository.saveAll(users);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}







