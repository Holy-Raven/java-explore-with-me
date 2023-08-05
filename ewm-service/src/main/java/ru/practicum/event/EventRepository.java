package ru.practicum.event;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.event.model.Event;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByInitiatorId(Long initiatorId, PageRequest pageRequest);

    Event findByInitiatorIdAndId(Long initiatorId, Long eventId);
}
