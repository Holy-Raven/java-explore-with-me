package ru.practicum.comments;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT c FROM Comment AS c " +
            "WHERE :event = c.event.id " +
            "AND (CAST(:rangeStart AS date) IS NULL AND CAST(:rangeStart AS date) IS NULL)" +
            "OR (CAST(:rangeStart AS date) IS NULL AND c.created < CAST(:rangeEnd AS date)) " +
            "OR (CAST(:rangeEnd AS date) IS NULL AND c.created > CAST(:rangeStart AS date)) " +
            "GROUP BY c.id " +
            "ORDER BY c.id ASC")
    List<Comment> getCommentsByEventId(@Param("event") Long event,
                                       @Param("rangeStart") LocalDateTime rangeStart,
                                       @Param("rangeEnd") LocalDateTime rangeEnd,
                                       PageRequest pageRequest);

    @Query(value = "SELECT c FROM Comment AS c " +
            "WHERE :user = c.user.id " +
            "AND (CAST(:rangeStart AS date) IS NULL AND CAST(:rangeStart AS date) IS NULL)" +
            "OR (CAST(:rangeStart AS date) IS NULL AND c.created < CAST(:rangeEnd AS date)) " +
            "OR (CAST(:rangeEnd AS date) IS NULL AND c.created > CAST(:rangeStart AS date)) " +
            "GROUP BY c.id " +
            "ORDER BY c.id ASC")
    List<Comment> getCommentsByUserId(@Param("user") Long user,
                                      @Param("rangeStart") LocalDateTime rangeStart,
                                      @Param("rangeEnd") LocalDateTime rangeEnd,
                                      PageRequest pageRequest);

    @Query(value = "SELECT c FROM Comment AS c " +
            "WHERE (CAST(:rangeStart AS date) IS NULL AND CAST(:rangeStart AS date) IS NULL)" +
            "OR (CAST(:rangeStart AS date) IS NULL AND c.created < CAST(:rangeEnd AS date)) " +
            "OR (CAST(:rangeEnd AS date) IS NULL AND c.created > CAST(:rangeStart AS date)) " +
            "GROUP BY c.id " +
            "ORDER BY c.id ASC")
    List<Comment> getComments(@Param("rangeStart") LocalDateTime rangeStart,
                              @Param("rangeEnd") LocalDateTime rangeEnd,
                              PageRequest pageRequest);
}
