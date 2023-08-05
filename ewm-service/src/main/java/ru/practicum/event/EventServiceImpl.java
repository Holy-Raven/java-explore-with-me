package ru.practicum.event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.category.Category;
import ru.practicum.event.dto.EventFullDto;
import ru.practicum.event.dto.EventNewDto;
import ru.practicum.event.dto.EventShortDto;
import ru.practicum.event.dto.EventUpdateDto;
import ru.practicum.event.model.Event;
import ru.practicum.event.model.Location;
import ru.practicum.exception.ValidationException;
import ru.practicum.user.User;
import ru.practicum.util.State;
import ru.practicum.util.StateAction;
import ru.practicum.util.UnionService;

import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final UnionService unionService;
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    @Override
    @Transactional
    public EventFullDto addEvent(Long userId, EventNewDto eventNewDto) {

        User user = unionService.getUserOrNotFound(userId);
        Category category = unionService.getCategoryOrNotFound(eventNewDto.getCategory());
        Location location = locationRepository.save(LocationMapper.returnLocation(eventNewDto.getLocation()));
        Event event = EventMapper.returnEvent(eventNewDto, category, location, user);
        eventRepository.save(event);

        return EventMapper.returnEventFullDto(event);
    }

    @Override
    public List<EventShortDto> getAllEventsByUserId(Long userId, Integer from, Integer size) {

        unionService.getUserOrNotFound(userId);
        PageRequest pageRequest = PageRequest.of(from / size, size);
        List<Event> events = eventRepository.findAllByInitiatorId(userId, pageRequest);

        return EventMapper.returnEventShortDtoList(events);
    }

    @Override
    public EventFullDto getUserEventById(Long userId, Long eventId) {

        unionService.getUserOrNotFound(userId);
        unionService.getEventOrNotFound(eventId);
        Event event = eventRepository.findByInitiatorIdAndId(userId,eventId);

        return EventMapper.returnEventFullDto(event);
    }

    @Override
    @Transactional
    public EventFullDto updateEventByUserId(EventUpdateDto eventUpdateDto, Long userId, Long eventId) {

        User user = unionService.getUserOrNotFound(userId);
        Event event = unionService.getEventOrNotFound(eventId);

        if (!user.getId().equals(event.getInitiator().getId())) {
            throw new ValidationException(String.format("the user %s is not the initiator of the event %s.",userId, eventId));
        }

        if (eventUpdateDto.getAnnotation() != null && !eventUpdateDto.getAnnotation().isBlank()) {
            event.setAnnotation(eventUpdateDto.getAnnotation());
        }
        if (eventUpdateDto.getCategory() != null) {
            event.setCategory(unionService.getCategoryOrNotFound(eventUpdateDto.getCategory()));
        }
        if (eventUpdateDto.getDescription() != null && !eventUpdateDto.getDescription().isBlank()) {
            event.setDescription(eventUpdateDto.getDescription());
        }
        if (eventUpdateDto.getEventDate() != null) {
            event.setEventDate(eventUpdateDto.getEventDate());
        }
        if (eventUpdateDto.getLocation() != null) {
            event.setLocation(LocationMapper.returnLocation(eventUpdateDto.getLocation()));
        }
        if (eventUpdateDto.getPaid() != null) {
            event.setPaid(eventUpdateDto.getPaid());
        }
        if (eventUpdateDto.getParticipantLimit() != null) {
            event.setParticipantLimit(eventUpdateDto.getParticipantLimit());
        }
        if (eventUpdateDto.getRequestModeration() != null) {
            event.setRequestModeration(eventUpdateDto.getRequestModeration());
        }
        if (eventUpdateDto.getStateAction() != null) {
            if (eventUpdateDto.getStateAction() == StateAction.PUBLISH_EVENT) {
                event.setState(State.PUBLISHED);
                event.setPublishedOn(LocalDateTime.now());
            } else if (eventUpdateDto.getStateAction() == StateAction.REJECT_EVENT ||
                eventUpdateDto.getStateAction() == StateAction.CANCEL_REVIEW) {
                event.setState(State.CANCELED);
            } else if (eventUpdateDto.getStateAction() == StateAction.SEND_TO_REVIEW) {
                event.setState(State.PENDING);
            }
        }
        if (eventUpdateDto.getTitle() != null && !eventUpdateDto.getTitle().isBlank()) {
            event.setTitle(eventUpdateDto.getTitle());
        }

        locationRepository.save(event.getLocation());
        Event updateEvent = eventRepository.save(event);

        return EventMapper.returnEventFullDto(updateEvent);
    }

    @Override
    @Transactional
    public EventFullDto updateEventByAdmin(EventNewDto eventNewDto, Long eventId) {
        return null;
    }

    @Override
    public List<EventFullDto> getEventsByAdmin(List<Long> users, List<Long> categories, List<String> states, LocalDateTime startTime, LocalDateTime endTime, Integer from, Integer size) {
        return null;
    }

}