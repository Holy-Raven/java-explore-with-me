package ru.practicum.category.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class CategoryDto {

    private Long id;

    @Size(max = 50, message = "name must be less than 50")
    @NotBlank(message = "name cannot be empty and consist only of spaces.")
    private String name;
}
