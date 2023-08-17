package ru.practicum.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.user.dto.UserShortDto;

import ru.practicum.category.dto.CategoryDto;
import ru.practicum.util.enums.State;

import java.time.LocalDateTime;

import static ru.practicum.Util.DATE_FORMAT;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventFullDto {

    String annotation;

    CategoryDto category;

    Long confirmedRequests;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    LocalDateTime createdOn;

    String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    LocalDateTime eventDate;

    Long id;

    UserShortDto initiator;

    LocationDto location;

    Boolean paid;

    Long participantLimit;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    LocalDateTime publishedOn;

    Boolean requestModeration;

    State state;

    String title;

    Long views;
}