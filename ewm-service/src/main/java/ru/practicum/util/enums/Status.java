package ru.practicum.util.enums;

import ru.practicum.exception.ValidationException;

public enum Status {

    PENDING,
    CONFIRMED,
    CANCELED,
    REJECTED;

    public static State getStatusValue(String status) {
        try {
            return State.valueOf(status);
        } catch (Exception e) {
            throw new ValidationException("Unknown status: " + status);
        }
    }
}