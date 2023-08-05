package ru.practicum.util;

import ru.practicum.exception.UnsupportedStateException;

public enum State {
    PENDING,
    PUBLISHED,
    CANCELED;

    public static State getStateValue(String state) {
        try {
            return State.valueOf(state);
        } catch (Exception e) {
            throw new UnsupportedStateException("Unknown state: " + state);
        }
    }
}