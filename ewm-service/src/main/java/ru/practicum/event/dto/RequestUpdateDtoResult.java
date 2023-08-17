package ru.practicum.event.dto;

import lombok.AccessLevel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.request.dto.RequestDto;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestUpdateDtoResult {

    List<RequestDto> confirmedRequests;

    List<RequestDto> rejectedRequests;
}