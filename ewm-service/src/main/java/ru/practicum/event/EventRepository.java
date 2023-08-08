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

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByInitiatorId(Long initiatorId, PageRequest pageRequest);

    Event findByInitiatorIdAndId(Long initiatorId, Long eventId);

    @Query(value = "SELECT e FROM Event AS e " +

            "WHERE (:users IS NULL OR e.initiator.id IN :users) " +
            "AND (:states IS NULL OR e.state IN :states) " +
            "AND (:categories IS NULL OR e.category.id IN :categories) " +
            "AND ((CAST(:rangeStart as date) IS NOT NULL AND CAST(:rangeEnd as date) IS NOT NULL " +
            "AND e.eventDate between CAST(:rangeStart as date) AND CAST(:rangeEnd as date)) " +
            "OR (CAST(:rangeStart as date) IS NULL AND CAST(:rangeStart as date) IS NULL))" +
            "OR (CAST(:rangeStart as date) IS NULL AND e.eventDate < CAST(:rangeEnd as date)) " +
            "OR (CAST(:rangeEnd as date) IS NULL AND e.eventDate > CAST(:rangeStart as date)) " +
            "GROUP BY e.id " +
            "ORDER BY e.id ASC")

    List<Event> findEventsByAdminForParam(@Param("users") List<Long> users,
                                          @Param("states") List<State> states,
                                          @Param("categories") List<Long> categories,
                                          @Param("rangeStart") LocalDateTime rangeStart,
                                          @Param("rangeEnd") LocalDateTime rangeEnd,
                                          PageRequest pageRequest);
}