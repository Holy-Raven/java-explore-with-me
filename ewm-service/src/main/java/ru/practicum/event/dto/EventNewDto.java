package ru.practicum.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.event.model.Location;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventNewDto {


    @NotBlank
    @Size(min = 20, max = 2000, message = "annotation must be greater than 20 and less than 2000")
    String annotation;

    @NotBlank
    Long category;

    @NotBlank
    @Size(min = 20, max = 7000, message = "description must be greater than 20 and less than 7000")
    String description;

    @NotBlank
    @FutureOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;

    @NotBlank
    Location location;

    @Builder.Default
    Boolean paid = false;

    @Builder.Default
    Long participantLimit = 0L;

    @Builder.Default
    Boolean requestModeration = true;

    @NotBlank
    @Size(min = 3, max = 120, message = "title must be greater than 3 and less than 120")
    String title;
}