package com.employee.controllers;

import com.employee.models.User;
import com.employee.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/main")
    public String createUser(@RequestParam(value = "firstname", required = false) String firstName,
                             @RequestParam(value = "lastname", required = false) String lastName,
                             @RequestParam(value = "companyid", required = false) int companyId,
                             @RequestParam(value = "role", required = false) String role,
                             Model model) {

        Long userID = userService.addUser(firstName, lastName, companyId, role).getId();
        model.addAttribute("userID", userID);
        return "/main";
    }

    @GetMapping("/list")
    public String listData(Model model) {

        Iterable users = userService.userList();
        model.addAttribute("userList", users);
        return "/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable(value = "id", required = false) Long id,
                             Model model) {
        userService.deleteUser(id);
        return "redirect:/list";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable(value = "id") Long id,
                           Model model) {

        model.addAttribute("user", userService.editUser(id));
        return "/edit";

    }

    @PostMapping("/edit/{id}")
    public String updUser(@PathVariable(value = "id", required = false) Long id,
                          @RequestParam(value = "firstname", required = false) String firstName,
                          @RequestParam(value = "lastname", required = false) String lastName,
                          @RequestParam(value = "companyid", required = false) Integer companyId,
                          @RequestParam(value = "role", required = false) String role,
                          Model model) {

        User user = userService.updateUser(id, firstName, lastName, companyId, role);
        model.addAttribute("user", user);

        return "/edit";
    }

    @PostMapping("/csv-upload")
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             Model model) {
        System.out.println("Upload was pressed");
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload");
            //model.addAttribute("status", false);
            System.out.println("file is EMPTY");
        } else {

            System.out.println("File NOT empty. Call service");

                userService.csvToUsers(file);
            }

        return "/main";

    }
}









