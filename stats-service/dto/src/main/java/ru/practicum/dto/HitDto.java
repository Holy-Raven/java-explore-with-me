package ru.practicum.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
public class HitDto {

    private Long id;

    @NotBlank(message = "app cannot be empty and consist only of spaces.\"")
    private String app;

    @NotBlank(message = "uri cannot be empty and consist only of spaces.")
    private String uri;

    @NotBlank(message = "ip cannot be empty and consist only of spaces.")
    private String ip;

    @NotBlank(message = "timestamp cannot be empty and consist only of spaces.")
    private String timestamp;
}