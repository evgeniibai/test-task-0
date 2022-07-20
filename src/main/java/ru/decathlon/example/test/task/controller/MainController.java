package ru.decathlon.example.test.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.decathlon.example.test.task.model.User;
import ru.decathlon.example.test.task.service.ErrorLogService;
import ru.decathlon.example.test.task.service.UserService;

import java.util.List;

@Controller
@RequestMapping(value = "/users")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('read')")
public class MainController {

    private final UserService userService;
    private final ErrorLogService logService;

    @GetMapping
    public String showAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
