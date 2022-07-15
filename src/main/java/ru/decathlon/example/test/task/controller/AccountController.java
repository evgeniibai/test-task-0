package ru.decathlon.example.test.task.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.decathlon.example.test.task.dto.RequestUserDTO;
import ru.decathlon.example.test.task.model.User;
import ru.decathlon.example.test.task.repository.ErrorRepository;
import ru.decathlon.example.test.task.repository.UserRepository;

@Controller
@RequestMapping(value = "/account")
@RequiredArgsConstructor
public class AccountController {

    private final UserRepository userRepository;

    @GetMapping(value = "/create-account")
    public String createAccountPage() {
        return "create-account";
    }

    @PostMapping(value = "/create-account")
    public String createAccount(@RequestBody RequestUserDTO userDTO) {
        User user = userDTO.toUser();
        userRepository.save(user);
        return "redirect:/welcome";
    }
}
