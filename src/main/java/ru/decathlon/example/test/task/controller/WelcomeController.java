package ru.decathlon.example.test.task.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {

    @PreAuthorize("hasAuthority('read')")
    @GetMapping
    public String welcomePage() {
        return "welcome";
    }
}
