package ru.decathlon.example.test.task.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.decathlon.example.test.task.exception.MismatchedPasswordsException;
import ru.decathlon.example.test.task.model.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RequestUserDTO(@NotBlank(message = "This field is required!")
                             @Size(min = 4, max = 32, message = "Username must be between 4 and 32 characters!")
                             String username,
                             @NotBlank(message = "This field is required!")
                             @Size(min = 8, message = "Password must be over 8 characters!")
                             String password,
                             @NotBlank(message = "This field is required!")
                             String confirmedPassword) {

    public User toUser() {
        if (!isValid(password, confirmedPassword)) {
            throw new MismatchedPasswordsException("Passwords don't match!");
        }

        return new User(username, password);
    }

    private boolean isValid(String password, String confirmedPassword) {
        return password.equals(confirmedPassword);
    }
}
