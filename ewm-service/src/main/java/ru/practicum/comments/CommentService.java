package ru.practicum.comments;

import ru.practicum.comments.dto.CommentFullDto;
import ru.practicum.comments.dto.CommentNewDto;
import ru.practicum.comments.dto.CommentShortDto;
import ru.practicum.comments.dto.CommentUpdateDto;

import java.util.List;

public interface CommentService {

    //private
    CommentFullDto addComment(Long userId, Long eventId, CommentNewDto commentNewDto);
    CommentFullDto updateComment(CommentUpdateDto commentUpdateDto);
    void deletePrivateComment(Long userId, Long commentId);
    List<CommentShortDto> getCommentsByUserId(String rangeStart, String rangeEnd, Long userId, Integer from, Integer size);

    //admin
    List<CommentFullDto> getComments(String rangeStart, String rangeEnd, Integer from, Integer size);
    void  deleteAdminComment(Long commentId);


    //public
    List<CommentShortDto> getCommentsByEventId(String rangeStart, String rangeEnd, Long eventId, Integer from, Integer size);



}
