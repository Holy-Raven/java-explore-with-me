package ru.practicum.event;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.event.model.Event;
import ru.practicum.util.enums.State;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByInitiatorId(Long initiatorId, PageRequest pageRequest);

    Event findByInitiatorIdAndId(Long initiatorId, Long eventId);

    List<Event> findByCategoryId(Long categoryId);

    Set<Event> findByIdIn(Set<Long> events);

    @Query(value = "SELECT e FROM Event AS e " +
            "WHERE (:users IS NULL OR e.initiator.id IN :users) " +
            "AND (:states IS NULL OR e.state IN :states) " +
            "AND (:categories IS NULL OR e.category.id IN :categories) " +
            "OR (:rangeStart IS NULL AND :rangeStart IS NULL)" +
            "OR (:rangeStart IS NULL AND e.eventDate < :rangeEnd) " +
            "OR (:rangeEnd IS NULL AND e.eventDate > :rangeStart) " +
            "GROUP BY e.id " +
            "ORDER BY e.id ASC")

    List<Event> findEventsByAdminFromParam(@Param("users") List<Long> users,
                                           @Param("states") List<State> states,
                                           @Param("categories") List<Long> categories,
                                           @Param("rangeStart") LocalDateTime rangeStart,
                                           @Param("rangeEnd") LocalDateTime rangeEnd,
                                           PageRequest pageRequest);

    @Query(value = "SELECT e FROM Event AS e " +
            "WHERE (e.state = 'PUBLISHED') " +
            "AND (:text IS NULL) " +
            "OR (LOWER(e.annotation) LIKE LOWER(CONCAT('%', :text, '%'))) " +
            "OR (LOWER(e.description) LIKE LOWER(CONCAT('%', :text, '%'))) " +
            "OR (LOWER(e.title) LIKE LOWER(CONCAT('%', :text, '%'))) " +
            "AND (:categories IS NULL OR e.category.id IN :categories) " +
            "AND (:paid IS NULL OR e.paid = :paid) " +
            "OR (:rangeStart IS NULL AND :rangeStart IS NULL)" +
            "OR (:rangeStart IS NULL AND e.eventDate < :rangeEnd) " +
            "OR (:rangeEnd IS NULL AND e.eventDate > :rangeStart) " +
            "AND (e.confirmedRequests < e.participantLimit OR :onlyAvailable = FALSE)" +
            "GROUP BY e.id " +
            "ORDER BY LOWER(:sort) ASC")

    List<Event> findEventsByPublicFromParam(@Param("text") String text,
                                            @Param("categories") List<Long> categories,
                                            @Param("paid") Boolean paid,
                                            @Param("rangeStart") LocalDateTime startTime,
                                            @Param("rangeEnd") LocalDateTime endTime,
                                            @Param("onlyAvailable") Boolean onlyAvailable,
                                            @Param("sort") String sort,
                                            PageRequest pageRequest);
}