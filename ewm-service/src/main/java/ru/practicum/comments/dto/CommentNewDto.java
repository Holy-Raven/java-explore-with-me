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
public class CommentNewDto {

    @NotBlank(message = "message cannot be empty and consist only of spaces.")
    @Size(max = 500, message = "message must less than 500")
    String message;
}
