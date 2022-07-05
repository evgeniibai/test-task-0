package ru.decathlon.example.test.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.decathlon.example.test.task.model.ErrorLog;

public interface ErrorRepository extends JpaRepository<ErrorLog, Integer> {
}
