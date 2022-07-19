package ru.decathlon.example.test.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.decathlon.example.test.task.dto.RequestUserDTO;
import ru.decathlon.example.test.task.model.User;
import ru.decathlon.example.test.task.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/account/create-account")
@RequiredArgsConstructor
public class CreateAccountController {

    private final UserService userService;

    @GetMapping
    public String createAccountPage(RequestUserDTO userDTO) {
        return "create-account";
    }

    @PostMapping
    public String createAccount(@Valid RequestUserDTO userDTO) {
        User user = userDTO.toUser();
        userService.saveUser(user);
        return "redirect:/welcome";
    }
}
