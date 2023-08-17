package ru.practicum.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.event.EventService;
import ru.practicum.event.dto.EventFullDto;
import ru.practicum.event.dto.EventUpdateDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/events")
public class EventAdminController {

    private final EventService eventService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<EventFullDto> getEventsByAdmin(@RequestParam(required = false, name = "users") List<Long> users,
                                               @RequestParam(required = false, name = "states") List<String> states,
                                               @RequestParam(required = false, name = "categories") List<Long> categories,
                                               @RequestParam(required = false, name = "rangeStart") String rangeStart,
                                               @RequestParam(required = false, name = "rangeEnd") String rangeEnd,
                                               @PositiveOrZero @RequestParam(name = "from", defaultValue = "0") Integer from,
                                               @Positive @RequestParam(name = "size", defaultValue = "10") Integer size) {

        log.info("Get all events with parameters: users = {}, states = {}, categories = {}, rangeStart = {}, rangeEnd = {}, from = {}, size = {}", users, states, categories, rangeStart, rangeEnd, from, size);
        return eventService.getEventsByAdmin(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PatchMapping("/{eventId}")
    @ResponseStatus(value = HttpStatus.OK)
    public EventFullDto updateEventByAdmin(@Valid @RequestBody EventUpdateDto eventUpdateDto,
                                           @PathVariable Long eventId) {

        log.info("Admin update Event {} ", eventId);
        return eventService.updateEventByAdmin(eventUpdateDto, eventId);
    }
}