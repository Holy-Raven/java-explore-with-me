package ru.practicum.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Class<?> entityClass, String message) {
        super("Entity " + entityClass.getSimpleName() + " not found. " + message);
    }
}