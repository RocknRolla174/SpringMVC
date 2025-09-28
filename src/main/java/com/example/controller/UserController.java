package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import com.example.util.HtmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public void showAllUsers(HttpServletResponse response) throws IOException {
        List<User> users = userService.getAllUsers();
        String html = HtmlUtil.generateUserListHtml(users);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(html);
    }

    @PostMapping("/users/add")
    public void addUser(@RequestParam String name,
                        @RequestParam String email,
                        @RequestParam Integer age,
                        HttpServletResponse response) throws IOException {
        User user = new User(name, email, age);
        userService.saveUser(user);
        response.sendRedirect("/SpringMVC_war_exploded/users");
    }

    @PostMapping("/users/update")
    public void updateUser(@RequestParam Long id,
                           @RequestParam String name,
                           @RequestParam String email,
                           @RequestParam Integer age,
                           HttpServletResponse response) throws IOException {
        User user = userService.getUserById(id);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setAge(age);
            userService.updateUser(user);
        }
        response.sendRedirect("/SpringMVC_war_exploded/users");
    }

    @PostMapping("/users/delete")
    public void deleteUser(@RequestParam Long id, HttpServletResponse response) throws IOException {
        userService.deleteUser(id);
        response.sendRedirect("/SpringMVC_war_exploded/users");
    }
}