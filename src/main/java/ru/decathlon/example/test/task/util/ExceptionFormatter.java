package ru.decathlon.example.test.task.util;

public final class ExceptionFormatter {

    private ExceptionFormatter() {
    }

    public static String format(Exception ex) {
        StringBuilder sb = new StringBuilder();
        sb.append(ex).append("\n");

        for (StackTraceElement element : ex.getStackTrace()) {
            sb.append("\tat ").append(element).append("\n");
        }

        return sb.toString();
    }
}
