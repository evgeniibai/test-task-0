package ru.decathlon.example.test.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.decathlon.example.test.task.model.ErrorLog;

import java.util.List;

public interface ErrorRepository extends JpaRepository<ErrorLog, Integer> {
    List<ErrorLog> findAllByErrorName(String errorName);
}
