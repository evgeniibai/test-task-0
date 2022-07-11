package ru.decathlon.example.test.task.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Permission {
    READ("read"),
    WRITE("write");

    private final String action;
}
