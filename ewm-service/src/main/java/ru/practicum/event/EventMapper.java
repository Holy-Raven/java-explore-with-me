package ru.practicum.event;

import lombok.experimental.UtilityClass;
import ru.practicum.category.Category;
import ru.practicum.category.CategoryMapper;
import ru.practicum.event.dto.EventFullDto;
import ru.practicum.event.dto.EventNewDto;
import ru.practicum.event.dto.EventShortDto;
import ru.practicum.event.model.Event;
import ru.practicum.event.model.Location;
import ru.practicum.user.User;
import ru.practicum.user.UserMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static ru.practicum.util.enums.State.PENDING;

@UtilityClass
public class EventMapper {

    public Event returnEvent(EventNewDto eventNewDto, Category category, Location location, User user) {
        Event event = Event.builder()
                .annotation(eventNewDto.getAnnotation())
                .category(category)
                .description(eventNewDto.getDescription())
                .eventDate(eventNewDto.getEventDate())
                .initiator(user)
                .location(location)
                .paid(eventNewDto.getPaid())
                .participantLimit(eventNewDto.getParticipantLimit())
                .requestModeration(eventNewDto.getRequestModeration())
                .createdOn(LocalDateTime.now())
                .views(0L)
                .state(PENDING)
                .confirmedRequests(0L)
                .title(eventNewDto.getTitle())
                .build();
        return event;
    }

    public EventFullDto returnEventFullDto(Event event) {
        EventFullDto eventFullDto = EventFullDto.builder()
                .annotation(event.getAnnotation())
                .category(CategoryMapper.returnCategoryDto(event.getCategory()))
                .confirmedRequests(event.getConfirmedRequests())
                .createdOn(event.getCreatedOn())
                .description(event.getDescription())
                .eventDate(event.getEventDate())
                .id(event.getId())
                .initiator(UserMapper.returnUserShortDto(event.getInitiator()))
                .location(LocationMapper.returnLocationDto(event.getLocation()))
                .paid(event.getPaid())
                .participantLimit(event.getParticipantLimit())
                .publishedOn(event.getPublishedOn())
                .requestModeration(event.getRequestModeration())
                .state(event.getState())
                .title(event.getTitle())
                .views(event.getViews())
                .build();
        return eventFullDto;
    }

    public EventShortDto returnEventShortDto(Event event) {

        EventShortDto eventShortDto = EventShortDto.builder()
                .annotation(event.getAnnotation())
                .category(CategoryMapper.returnCategoryDto(event.getCategory()))
                .confirmedRequests(event.getConfirmedRequests())
                .eventDate(event.getEventDate())
                .id(event.getId())
                .initiator(UserMapper.returnUserShortDto(event.getInitiator()))
                .paid(event.getPaid())
                .title(event.getTitle())
                .views(event.getViews())
                .build();
        return eventShortDto;
    }

    public List<EventFullDto> returnEventFullDtoList(Iterable<Event> events) {
        List<EventFullDto> result = new ArrayList<>();

        for (Event event : events) {
            result.add(returnEventFullDto(event));
        }
        return result;
    }

    public List<EventShortDto> returnEventShortDtoList(Iterable<Event> events) {
        List<EventShortDto> result = new ArrayList<>();

        for (Event event : events) {
            result.add(returnEventShortDto(event));
        }
        return result;
    }
}