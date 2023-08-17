package ru.practicum.util;

import ru.practicum.category.Category;
import ru.practicum.compilation.Compilation;
import ru.practicum.event.model.Event;
import ru.practicum.request.Request;
import ru.practicum.user.User;

import java.time.LocalDateTime;

public interface UnionService {

    User getUserOrNotFound(Long userId);

    Category getCategoryOrNotFound(Long categoryId);

    Event getEventOrNotFound(Long eventId);

    Request getRequestOrNotFound(Long requestId);

    Compilation getCompilationOrNotFound(Long compId);

    LocalDateTime parseDate(String date);
}
