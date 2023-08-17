package ru.practicum.request;

import ru.practicum.request.dto.RequestDto;

import java.util.List;

public interface RequestService {

    RequestDto addRequest(Long userId, Long eventId);

    List<RequestDto> getRequestsByUserId(Long userId);

    RequestDto cancelRequest(Long userId, Long requestId);
}
