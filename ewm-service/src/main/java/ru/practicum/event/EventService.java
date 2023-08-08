package ru.practicum.event;

import ru.practicum.event.dto.*;
import ru.practicum.request.dto.RequestDto;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    EventFullDto addEvent(Long userId, EventNewDto eventnewDto) ;

    List<EventShortDto> getAllEventsByUserId(Long userId, Integer from, Integer size);

    EventFullDto getUserEventById(Long userId, Long eventId);

    EventFullDto updateEventByUserId(EventUpdateDto eventUpdateDto, Long userId, Long eventId);

    List<RequestDto> getRequestsForEventIdByUserId(Long userId, Long eventId);

    RequestUpdateDtoResult updateStatusRequestsForEventIdByUserId(RequestUpdateDtoRequest requestDto, Long userId, Long eventId);

    EventFullDto updateEventByAdmin(EventUpdateDto eventUpdateDto, Long eventId);

    List<EventFullDto> getEventsByAdmin(List<Long> users, List<String> states, List<Long> categories, LocalDateTime startTime, LocalDateTime endTime, Integer from, Integer size);


}
