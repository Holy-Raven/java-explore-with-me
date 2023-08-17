package ru.practicum.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static ru.practicum.Util.DATE_FORMAT;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HitDto {

    Long id;

    @NotBlank(message = "app cannot be empty and consist only of spaces.")
    String app;

    @NotBlank(message = "uri cannot be empty and consist only of spaces.")
    String uri;

    @NotBlank(message = "ip cannot be empty and consist only of spaces.")
    String ip;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    LocalDateTime timestamp;
}