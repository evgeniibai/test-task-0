package ru.decathlon.example.test.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.decathlon.example.test.task.exception.UserNotFoundException;
import ru.decathlon.example.test.task.model.User;
import ru.decathlon.example.test.task.repository.UserRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    public final UserRepository userRepository;
    public final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUuid(String uuid) {
        return userRepository.findByUuid(uuid)
                .orElseThrow(() -> new UserNotFoundException("User with UUID: " + uuid + " is not found!"));
    }

    public User saveUser(User user) {
        //TODO
        return userRepository.save(user);
    }
}
