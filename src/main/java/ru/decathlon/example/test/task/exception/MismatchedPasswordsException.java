package ru.decathlon.example.test.task.exception;

public class MismatchedPasswordsException extends RuntimeException {
    public MismatchedPasswordsException(String message) {
        super(message);
    }

    public MismatchedPasswordsException(String message, Throwable cause) {
        super(message, cause);
    }
}
