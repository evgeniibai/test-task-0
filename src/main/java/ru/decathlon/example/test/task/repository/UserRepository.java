package ru.decathlon.example.test.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.decathlon.example.test.task.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
