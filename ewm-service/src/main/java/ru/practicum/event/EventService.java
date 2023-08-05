package ru.practicum.event;

import ru.practicum.event.dto.EventFullDto;
import ru.practicum.event.dto.EventNewDto;
import ru.practicum.event.dto.EventShortDto;
import ru.practicum.event.dto.EventUpdateDto;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    EventFullDto addEvent(Long userId, EventNewDto eventnewDto) ;

    List<EventShortDto> getAllEventsByUserId(Long userId, Integer from, Integer size);

    EventFullDto getUserEventById(Long userId, Long eventId);

    EventFullDto updateEventByUserId(EventUpdateDto eventUpdateDto, Long userId, Long eventId);

    EventFullDto updateEventByAdmin(EventNewDto eventNewDto, Long eventId);

    List<EventFullDto> getEventsByAdmin(List<Long> users, List<Long> categories, List<String> states, LocalDateTime startTime, LocalDateTime endTime, Integer from, Integer size);
}
