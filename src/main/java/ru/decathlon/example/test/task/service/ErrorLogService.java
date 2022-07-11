package ru.decathlon.example.test.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.decathlon.example.test.task.model.ErrorLog;
import ru.decathlon.example.test.task.repository.ErrorRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ErrorLogService {

    private final ErrorRepository errorRepository;

    public ErrorLog saveErrorLog(ErrorLog errorLog) {
        log.info("saveErrorLog: Saving " + errorLog + " in repository.");
        return errorRepository.save(errorLog);
    }

    public void deleteErrorLogById(int errorId) {
        log.info("deleteErrorLogById: Deleting error log with ID: " + errorId);
        errorRepository.deleteById(errorId);
    }

    public void deleteErrorLogByName(String errorName) {
        log.info("deleteErrorLogByName: Find out all error logs with error name: " + errorName);
        List<ErrorLog> errorLogList = errorRepository.findAllByErrorName(errorName);
        log.info("deleteErrorLogByName: Deleting all found error logs.");
        errorRepository.deleteAll(errorLogList);
    }

    public void deleteAllErrorLogs() {
        log.info("deleteAllErrorLogs: Deleting ALL error logs.");
        errorRepository.deleteAll();
    }
}
