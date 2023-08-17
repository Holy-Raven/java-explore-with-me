package ru.practicum.event;

import ru.practicum.event.dto.*;
import ru.practicum.request.dto.RequestDto;

import java.util.List;

public interface EventService {

    EventFullDto addEvent(Long userId, EventNewDto eventnewDto);

    List<EventShortDto> getAllEventsByUserId(Long userId, Integer from, Integer size);

    EventFullDto getUserEventById(Long userId, Long eventId);

    EventFullDto updateEventByUserId(EventUpdateDto eventUpdateDto, Long userId, Long eventId);

    List<RequestDto> getRequestsForEventIdByUserId(Long userId, Long eventId);

    RequestUpdateDtoResult updateStatusRequestsForEventIdByUserId(RequestUpdateDtoRequest requestDto, Long userId, Long eventId);

    EventFullDto updateEventByAdmin(EventUpdateDto eventUpdateDto, Long eventId);

    List<EventFullDto> getEventsByAdmin(List<Long> users, List<String> states, List<Long> categories, String startTime, String endTime, Integer from, Integer size);

    EventFullDto getEventById(Long eventId, String uri, String ip);

    List<EventShortDto> getEventsByPublic(String text, List<Long> categories, Boolean paid, String startTime, String endTime, Boolean onlyAvailable, String sort, Integer from, Integer size, String uri, String ip);
}
