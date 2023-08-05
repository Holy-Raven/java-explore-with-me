package ru.practicum.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.event.EventService;
import ru.practicum.event.dto.EventFullDto;
import ru.practicum.event.dto.EventNewDto;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

import static ru.practicum.Util.FORMATTER;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/events")
public class EventAdminController {

    private final EventService eventService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<EventFullDto> getEventsByAdmin(@RequestParam(required = false, name = "users") List<Long> users,
                                               @RequestParam(required = false, name = "categories") List<Long> categories,
                                               @RequestParam(required = false, name = "states") List<String> states,
                                               @RequestParam(required = false, name = "rangeStart") String start,
                                               @RequestParam(required = false, name = "rangeEnd") String end,
                                               @RequestParam(required = false, defaultValue = "0") Integer from,
                                               @RequestParam(required = false, defaultValue = "10") Integer size) {

        LocalDateTime startTime = LocalDateTime.parse(start, FORMATTER);
        LocalDateTime endTime = LocalDateTime.parse(end, FORMATTER);

        log.info("Get all events with parameters: users = {}, states = {}, categories = {}, rangeStart = {}, rangeEnd = {}, from = {}, size = {}", users, states, categories, start, end, from, size);
        return eventService.getEventsByAdmin(users, categories, states, startTime, endTime, from, size);
    }

    @PatchMapping("/{eventId}")
    @ResponseStatus(value = HttpStatus.OK)
    public EventFullDto updateEventByAdmin(@Valid @RequestBody EventNewDto eventNewDto,
                                 @PathVariable Long eventId) {


        return eventService.updateEventByAdmin(eventNewDto, eventId);
    }
}