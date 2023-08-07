package ru.practicum.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

import static ru.practicum.Util.DATE_FORMAT;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventNewDto {


    @NotBlank(message = "annotation cannot be empty and consist only of spaces.")
    @Size(min = 20, max = 2000, message = "annotation must be greater than 20 and less than 2000")
    String annotation;

    @NotNull
    Long category;

    @NotBlank(message = "description cannot be empty and consist only of spaces.")
    @Size(min = 20, max = 7000, message = "description must be greater than 20 and less than 7000")
    String description;

    @NotNull
    @FutureOrPresent
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    LocalDateTime eventDate;

    @NotNull
    LocationDto location;

    @NotNull
    @Builder.Default
    Boolean paid = false;

    @Builder.Default
    Long participantLimit = 0L;

    @Builder.Default
    Boolean requestModeration = true;

    @NotBlank(message = "title cannot be empty and consist only of spaces.")
    @Size(min = 3, max = 120, message = "title must be greater than 3 and less than 120")
    String title;
}