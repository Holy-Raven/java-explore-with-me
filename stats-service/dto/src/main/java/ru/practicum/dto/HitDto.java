package ru.practicum.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

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

    @NotBlank(message = "timestamp cannot be empty and consist only of spaces.")
    String timestamp;
}