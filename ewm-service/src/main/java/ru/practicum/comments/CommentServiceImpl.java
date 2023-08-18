package ru.practicum.comments;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.comments.dto.CommentFullDto;
import ru.practicum.comments.dto.CommentNewDto;
import ru.practicum.comments.dto.CommentShortDto;
import ru.practicum.comments.dto.CommentUpdateDto;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    //Private methods
    @Override
    @Transactional
    public CommentFullDto addComment(Long userId, Long eventId, CommentNewDto commentNewDto) {
        return null;
    }

    @Override
    @Transactional
    public CommentFullDto updateComment(CommentUpdateDto commentUpdateDto) {
        return null;
    }

    @Override
    @Transactional
    public void deletePrivateComment(Long userId, Long commentId) {

    }

    @Override
    public List<CommentShortDto> getCommentsByUserId(String rangeStart, String rangeEnd, Long userId, Integer from, Integer size) {
        return null;
    }



    // Admin methods
    @Override
    public List<CommentFullDto> getComments(String rangeStart, String rangeEnd, Integer from, Integer size) {
        return null;
    }

    @Override
    @Transactional
    public void deleteAdminComment(Long commentId) {

    }


    // Public methods
    @Override
    public List<CommentShortDto> getCommentsByEventId(String rangeStart, String rangeEnd, Long eventId, Integer from, Integer size) {
        return null;
    }
}
