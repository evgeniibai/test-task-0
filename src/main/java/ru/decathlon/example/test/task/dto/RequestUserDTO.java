package ru.decathlon.example.test.task.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.decathlon.example.test.task.model.User;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RequestUserDTO(String username, String password) {

    public User toUser() {
        return new User(username, password);
    }
}
