package ru.practicum.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.exception.NotFoundException;
import ru.practicum.user.User;
import ru.practicum.user.UserRepository;

@Service
@RequiredArgsConstructor
public class UnionServiceImpl implements UnionService {

    private final UserRepository userRepository;

    @Override
    public void checkUser(Long userId) {

        if (!userRepository.existsById(userId)) {
            throw new NotFoundException(User.class, "User id " + userId + " not found.");
        }
    }
}