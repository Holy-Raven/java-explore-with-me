package ru.practicum.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.event.EventService;

import ru.practicum.event.dto.*;
import ru.practicum.request.dto.RequestDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}/events")
public class EventPrivateController {

    private final EventService eventService;


    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public EventFullDto addEvent(@Valid @RequestBody EventNewDto eventNewDto,
                                 @PathVariable Long userId) {

        log.info("User id {}, add Event {} ", userId, eventNewDto.getAnnotation());
        return eventService.addEvent(userId, eventNewDto);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<EventShortDto> getAllEventsByUserId(@PathVariable Long userId,
                                                    @PositiveOrZero @RequestParam(name = "from", defaultValue = "0") Integer from,
                                                    @Positive @RequestParam(name = "size", defaultValue = "10") Integer size) {

        log.info("List events for User Id {}. Where from = {}, size = {}", userId, from, size);
        return eventService.getAllEventsByUserId(userId, from, size);
    }

    @GetMapping("/{eventId}")
    @ResponseStatus(value = HttpStatus.OK)
    public EventFullDto getUserEventById(@PathVariable Long userId,
                                         @PathVariable Long eventId) {

        log.info("Get Event id {}, for User id {} ", eventId, userId);
        return eventService.getUserEventById(userId, eventId);
    }

    @PatchMapping("/{eventId}")
    @ResponseStatus(value = HttpStatus.OK)
    public EventFullDto updateEventByUserId(@RequestBody @Valid EventUpdateDto eventUpdateDto,
                                            @PathVariable Long userId,
                                            @PathVariable Long eventId) {

        log.info("User id {}, update Event {} ", eventId, eventUpdateDto.getAnnotation());
        return eventService.updateEventByUserId(eventUpdateDto, userId, eventId);
    }

    @GetMapping("/{eventId}/requests")
    @ResponseStatus(value = HttpStatus.OK)
    private List<RequestDto> getRequestsForEventIdByUserId(@PathVariable Long userId,
                                                           @PathVariable Long eventId) {

        log.info("Get all requests for event id{} by user Id{}.", eventId, userId);
        return eventService.getRequestsForEventIdByUserId(userId, eventId);
    }

    @PatchMapping("/{eventId}/requests")
    @ResponseStatus(value = HttpStatus.OK)
    private RequestUpdateDtoResult updateStatusRequestsForEventIdByUserId(@PathVariable Long userId,
                                                                          @PathVariable Long eventId,
                                                                          @RequestBody RequestUpdateDtoRequest requestDto) {

        log.info("Update status request for event id{}, by user id{}.", eventId,  userId);
        return eventService.updateStatusRequestsForEventIdByUserId(requestDto, userId, eventId);
    }
}