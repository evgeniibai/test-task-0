package ru.decathlon.example.test.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.decathlon.example.test.task.exception.UserAlreadyExistException;
import ru.decathlon.example.test.task.exception.UserNotFoundException;
import ru.decathlon.example.test.task.model.User;
import ru.decathlon.example.test.task.repository.UserRepository;

import java.util.List;
import java.util.Optional;

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
        log.info("Get user by UUID: " + uuid);
        return userRepository.findByUuid(uuid)
                .orElseThrow(() -> new UserNotFoundException("User with UUID: " + uuid + " is not found!"));
    }

    public void saveUser(User user) {
        log.info("Encoding user password");
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        log.info("Set encoded password to user");
        user.setPassword(encodedPassword);

        log.info("Checking the " + user + " for presence in the database.");
        if (alreadyExistingUser(user)) {
            throw new UserAlreadyExistException("There is an account with that username: " + user.getUsername());
        }

        log.info("Save user: " + user);
        userRepository.save(user);
    }

    private boolean alreadyExistingUser(User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());

        return existingUser.isPresent();
    }
}
