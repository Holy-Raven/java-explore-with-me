package ru.practicum.comments;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.comments.dto.CommentFullDto;
import ru.practicum.comments.dto.CommentNewDto;
import ru.practicum.comments.dto.CommentShortDto;
import ru.practicum.event.model.Event;
import ru.practicum.exception.ConflictException;
import ru.practicum.exception.ValidationException;
import ru.practicum.user.User;
import ru.practicum.util.UnionService;

import java.time.LocalDateTime;
import java.util.List;

import static ru.practicum.Util.CURRENT_TIME;

@Slf4j
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final UnionService unionService;
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public CommentFullDto addComment(Long userId, Long eventId, CommentNewDto commentNewDto) {

        User user = unionService.getUserOrNotFound(userId);
        Event event = unionService.getEventOrNotFound(eventId);

        Comment comment = CommentMapper.returnComment(commentNewDto, user,event);
        comment = commentRepository.save(comment);

        return CommentMapper.returnCommentFullDto(comment);
    }

    @Override
    @Transactional
    public CommentFullDto updateComment(Long userId, Long commentId, CommentNewDto commentNewDto) {

        Comment comment = unionService.getCommentOrNotFound(commentId);

        if (!userId.equals(comment.getUser().getId())) {
            throw new ConflictException(String.format("User %s is not the author of the comment %s.",userId, commentId));
        }

        if (commentNewDto.getMessage() != null && !commentNewDto.getMessage().isBlank()) {
            comment.setMessage(commentNewDto.getMessage());
        }

        comment = commentRepository.save(comment);

        return CommentMapper.returnCommentFullDto(comment);
    }

    @Override
    @Transactional
    public void deletePrivateComment(Long userId, Long commentId) {

        Comment comment = unionService.getCommentOrNotFound(commentId);
        unionService.getUserOrNotFound(userId);

        if (!comment.getUser().getId().equals(userId)) {
            throw new ConflictException(String.format("User %s is not the author of the comment %s.",userId, commentId));
        }

        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentShortDto> getCommentsByUserId(String rangeStart, String rangeEnd, Long userId, Integer from, Integer size) {

        unionService.getUserOrNotFound(userId);
        PageRequest pageRequest = PageRequest.of(from / size, size);

        LocalDateTime startTime = unionService.parseDate(rangeStart);
        LocalDateTime endTime = unionService.parseDate(rangeEnd);

        if (startTime != null && endTime != null) {
            if (startTime.isAfter(endTime)) {
                throw new ValidationException("Start must be after End");
            }
            if (endTime.isAfter(CURRENT_TIME) || startTime.isAfter(CURRENT_TIME)) {
                throw new ValidationException("date must be the past");
            }
        }

        List<Comment> commentList = commentRepository.getCommentsByUserId(userId, startTime, endTime, pageRequest);

        return CommentMapper.returnCommentShortDtoList(commentList);
    }

    @Override
    public List<CommentFullDto> getComments(String rangeStart, String rangeEnd, Integer from, Integer size) {

        PageRequest pageRequest = PageRequest.of(from / size, size);

        LocalDateTime startTime = unionService.parseDate(rangeStart);
        LocalDateTime endTime = unionService.parseDate(rangeEnd);

        if (startTime != null && endTime != null) {
            if (startTime.isAfter(endTime)) {
                throw new ValidationException("Start must be after End");
            }
            if (endTime.isAfter(CURRENT_TIME) || startTime.isAfter(CURRENT_TIME)) {
                throw new ValidationException("date must be the past");
            }
        }

        List<Comment> commentList = commentRepository.getComments(startTime, endTime, pageRequest);

        return CommentMapper.returnCommentFullDtoList(commentList);
    }

    @Override
    @Transactional
    public void deleteAdminComment(Long commentId) {

        unionService.getCommentOrNotFound(commentId);
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentShortDto> getCommentsByEventId(String rangeStart, String rangeEnd, Long eventId, Integer from, Integer size) {

        unionService.getEventOrNotFound(eventId);
        PageRequest pageRequest = PageRequest.of(from / size, size);

        LocalDateTime startTime = unionService.parseDate(rangeStart);
        LocalDateTime endTime = unionService.parseDate(rangeEnd);

        if (startTime != null && endTime != null) {
            if (startTime.isAfter(endTime)) {
                throw new ValidationException("Start must be after End");
            }
            if (endTime.isAfter(CURRENT_TIME) || startTime.isAfter(CURRENT_TIME)) {
                throw new ValidationException("date must be the past");
            }
        }

        List<Comment> commentList = commentRepository.getCommentsByEventId(eventId, startTime, endTime, pageRequest);

        return CommentMapper.returnCommentShortDtoList(commentList);
    }
}