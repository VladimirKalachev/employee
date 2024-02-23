package com.employee.controller;

import com.employee.model.User;
import com.employee.service.UserService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    private  final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String home(Model model) {
        model.addAttribute("title", "Index page");
        return "/index";
    }

    @PostMapping("/index")
    public String createUser(@RequestParam(value = "firstname", required = false) String firstName,
                             @RequestParam(value = "lastname", required = false) String lastName,
                             @RequestParam(value = "companyid", required = false) int companyId,
                             @RequestParam(value = "role", required = false) String role,
                             Model model) {

        Long userID = userService.addUser(firstName, lastName, companyId, role).getId();
        model.addAttribute("userID", userID);
        return "/index";
    }

    @GetMapping("/list")
    public String listData(Model model) {

        Iterable users = userService.userList();
        model.addAttribute("userList", users);
        return "/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable(value = "id", required = false) Long id) {
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
    public String uploadFile(@RequestParam(value = "file") MultipartFile file,
                             Model model) throws IOException {

        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload");

        } else {
            userService.CsvToUserBean(file);
        }

        return "redirect:/index";
    }

    @GetMapping("/download")
    public void exportCSV(HttpServletResponse response) throws Exception {
        try {
            String filename = "users.csv";

            response.setContentType("text/csv");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + filename + "\"");

            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(User.class);
            String[] header = {"id", "firstName", "lastName", "companyId", "role"};
            strategy.setColumnMapping(header);

            StatefulBeanToCsv<User> writer = new StatefulBeanToCsvBuilder<User>(response.getWriter())
                    .withMappingStrategy(strategy)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withOrderedResults(false)
                    .build();

            List<User> users = (List<User>) userService.userList();

            writer.write(users);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}









