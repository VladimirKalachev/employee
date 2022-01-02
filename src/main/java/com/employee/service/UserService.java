package com.employee.service;

import com.employee.model.User;
import com.employee.repo.UserRepository;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    /*
    public void csvToUsers(MultipartFile file) {
        System.out.println("Was called service upload CSV");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                   // .withMappingStrategy(strategy)
                    .withType(User.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List users = csvToBean.parse();
            System.out.println("users toString" + users.toString());
            userRepository.saveAll(users);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
//    public void csvToUsers(MultipartFile file) throws FileNotFoundException {
//        System.out.println("Was called vervice upload csv");
//
//        Map<String, String> map = new HashMap<>();
//
//        map.put("firstName", "firstName");
//        map.put("lastName", "lastName");
//        map.put("companyId", "companyId");
//        map.put("role", "role");
//
//        HeaderColumnNameTranslateMappingStrategy<User> strategy =
//                new HeaderColumnNameTranslateMappingStrategy<>();
//
//        strategy.setType(User.class);
//        strategy.setColumnMapping(map);
//
//        CSVReader csvReader = null;
//        try {
//            csvReader = new CSVReader(new FileReader
//                    ("C:\\Users\\pc\\Downloads\\users(5).csv"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        CsvToBean csvToBean = new CsvToBean();
//
////       // List<User> list = csvToBean.parse(strategy);
////
////        for(User user : list) {
////            System.out.println(user);
////        }
//
//    }


    public void CsvToUserBean(MultipartFile file) throws IOException {
        System.out.println("Was called service upload csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        List<User> users = new CsvToBeanBuilder<User>(reader)
                .withType(User.class)
                .build()
                .parse();

        users.forEach(System.out::println);

    }

}







