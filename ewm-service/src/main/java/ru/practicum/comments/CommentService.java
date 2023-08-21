package ru.practicum.comments;

import ru.practicum.comments.dto.CommentFullDto;
import ru.practicum.comments.dto.CommentNewDto;
import ru.practicum.comments.dto.CommentShortDto;

import java.util.List;

public interface CommentService {

    CommentFullDto addComment(Long userId, Long eventId, CommentNewDto commentNewDto);

    CommentFullDto updateComment(Long userId, Long commentId, CommentNewDto commentNewDto);

    void deletePrivateComment(Long userId, Long commentId);

    List<CommentShortDto> getCommentsByUserId(String rangeStart, String rangeEnd, Long userId, Integer from, Integer size);

    List<CommentFullDto> getComments(String rangeStart, String rangeEnd, Integer from, Integer size);

    void  deleteAdminComment(Long commentId);

    List<CommentShortDto> getCommentsByEventId(String rangeStart, String rangeEnd, Long eventId, Integer from, Integer size);
}
