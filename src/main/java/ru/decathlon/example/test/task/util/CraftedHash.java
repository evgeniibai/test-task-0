package ru.decathlon.example.test.task.util;

import java.util.UUID;

public final class CraftedHash {
    private CraftedHash() {
    }

    public static String hash() {
        return UUID.randomUUID().toString();
    }

    public static String hardHash(String targetStringEnd) {
        String uuid = "";
        boolean isRunning = true;
        while (isRunning) {
            uuid = UUID.randomUUID().toString();
            if (uuid.endsWith(targetStringEnd)) {
                isRunning = false;
            }
        }

        return uuid;
    }
}
