package ru.practicum.util;

import ru.practicum.category.Category;
import ru.practicum.user.User;

public interface UnionService {

    User getUserOrNotFound(Long userId);

    Category getCategoryOrNotFound(Long categoryId);
}
