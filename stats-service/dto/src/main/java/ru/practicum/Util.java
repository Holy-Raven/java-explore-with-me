package ru.practicum;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final LocalDateTime START_HISTORY = LocalDateTime.of(1970, 1, 1, 0, 0);
}
