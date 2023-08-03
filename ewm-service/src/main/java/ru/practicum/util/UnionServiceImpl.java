package ru.practicum.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.category.Category;
import ru.practicum.category.CategoryRepository;
import ru.practicum.exception.NotFoundException;
import ru.practicum.user.User;
import ru.practicum.user.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnionServiceImpl implements UnionService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public User getUserOrNotFound(Long userId) {

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new NotFoundException(User.class, "User id " + userId + " not found.");
        } else {
            return user.get();
        }
    }

    @Override
    public Category getCategoryOrNotFound(Long categoryId) {

        Optional<Category> category = categoryRepository.findById(categoryId);

        if (category.isEmpty()){
            throw new NotFoundException(User.class, "Category id " + categoryId + " not found.");
        } else {
            return category.get();
        }
    }
}