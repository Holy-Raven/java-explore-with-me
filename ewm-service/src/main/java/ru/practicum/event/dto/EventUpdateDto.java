package ru.practicum.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.util.enums.StateAction;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static ru.practicum.Util.DATE_FORMAT;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventUpdateDto {

    @Size(min = 20, max = 2000, message = "annotation must be greater than 20 and less than 2000")
    String annotation;

    Long category;

    @Size(min = 20, max = 7000, message = "description must be greater than 20 and less than 7000")
    String description;

    @FutureOrPresent
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    LocalDateTime eventDate;

    LocationDto location;

    Boolean paid;

    Long participantLimit;

    Boolean requestModeration;

    StateAction stateAction;

    @Size(min = 3, max = 120, message = "title must be greater than 3 and less than 120")
    String title;
}