package ru.practicum.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.category.Category;
import ru.practicum.category.CategoryRepository;
import ru.practicum.event.EventRepository;
import ru.practicum.event.model.Event;
import ru.practicum.exception.NotFoundException;
import ru.practicum.user.User;
import ru.practicum.user.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnionServiceImpl implements UnionService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final EventRepository eventRepository;

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
            throw new NotFoundException(Category.class, "Category id " + categoryId + " not found.");
        } else {
            return category.get();
        }
    }

    @Override
    public Event getEventOrNotFound(Long eventId) {

        Optional<Event> event = eventRepository.findById(eventId);

        if (event.isEmpty()){
            throw new NotFoundException(Event.class, "Category id " + eventId + " not found.");
        } else {
            return event.get();
        }
    }
}