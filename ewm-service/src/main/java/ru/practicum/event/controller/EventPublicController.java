package ru.practicum.event.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.event.EventService;
import ru.practicum.event.dto.EventFullDto;
import ru.practicum.event.dto.EventShortDto;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/events")
public class EventPublicController {

    public final EventService eventService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<EventShortDto> getEventsByPublic(@RequestParam(required = false, name = "text") String text,
                                                 @RequestParam(required = false, name = "categories") List<Long> categories,
                                                 @RequestParam(required = false, name = "paid") Boolean paid,
                                                 @RequestParam(required = false, name = "rangeStart") String rangeStart,
                                                 @RequestParam(required = false, name = "rangeEnd") String rangeEnd,
                                                 @RequestParam(required = false, defaultValue = "false", name = "onlyAvailable") Boolean onlyAvailable,
                                                 @RequestParam(required = false, name = "sort") String sort,
                                                 @PositiveOrZero @RequestParam(name = "from", defaultValue = "0") Integer from,
                                                 @Positive @RequestParam(name = "size", defaultValue = "10") Integer size,
                                                 HttpServletRequest request) {

        String uri = request.getRequestURI();
        String ip = request.getRemoteAddr();

        log.info("Get all events for public witch short info from parameters: text = {}, categories = {}, paid = {}, rangeStart = {}, rangeEnd = {}, onlyAvailable = {}, sort= {}, from = {}, size = {}",
                text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
        return eventService.getEventsByPublic(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size, uri, ip);
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public EventFullDto getEventById(@PathVariable Long id, HttpServletRequest request) {

        String uri = request.getRequestURI();
        String ip = request.getRemoteAddr();

        log.info("Get Event id {}", id);
        return eventService.getEventById(id, uri, ip);
    }
}