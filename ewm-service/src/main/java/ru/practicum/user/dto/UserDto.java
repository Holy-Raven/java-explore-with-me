package ru.practicum.user.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class UserDto {

    private Long id;

    @Size(min = 2, max = 250, message = "name must be greater than 2 and less than 250")
    @NotNull(message = "name cannot be empty.")
    @NotBlank(message = "name it cannot consist only of spaces.")
    private String name;

    @Email
    @Size(min = 6, max = 254, message = "Email must be greater than 6 and less than 254")
    @NotNull(message = "email cannot be empty.")
    @NotBlank(message = "email it cannot consist only of spaces.")
    private String email;
}