package ru.decathlon.example.test.task.util;

import java.util.UUID;

public final class CraftedHash {
    private CraftedHash() {
    }

    public static String hash() {
        String uuid = "";
        String targetStringEnd = "0000";
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
