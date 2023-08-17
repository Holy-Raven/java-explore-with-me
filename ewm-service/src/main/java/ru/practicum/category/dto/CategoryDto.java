package ru.practicum.category.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryDto {

    Long id;

    @Size(max = 50, message = "name must be less than 50")
    @NotBlank(message = "name cannot be empty and consist only of spaces.")
    String name;
}
