package ru.practicum.comments.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentUpdateDto {

    Long id;

    Long user;

    Long event;

    @NotBlank(message = "message cannot be empty and consist only of spaces.")
    @Size(max = 500, message = "message must be greater than 20 and less than 500")
    String message;
}
