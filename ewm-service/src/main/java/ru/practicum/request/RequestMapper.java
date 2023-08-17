package ru.practicum.request;

import lombok.experimental.UtilityClass;
import ru.practicum.event.model.Event;
import ru.practicum.request.dto.RequestDto;
import ru.practicum.user.User;
import ru.practicum.util.enums.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class RequestMapper {
    public RequestDto returnRequestDto(Request request) {
        RequestDto requestDto = RequestDto.builder()
                .id(request.getId())
                .created(request.getCreated())
                .event(request.getEvent().getId())
                .requester(request.getRequester().getId())
                .status(request.getStatus())
                .build();
        return requestDto;
    }

    public Request returnRequest(RequestDto requestDto, Event event, User user) {
        Request request = Request.builder()
                .id(requestDto.getId())
                .created(LocalDateTime.now())
                .event(event)
                .requester(user)
                .status(Status.PENDING)
                .build();
        return request;
    }

    public List<RequestDto> returnRequestDtoList(Iterable<Request> requests) {
        List<RequestDto> result = new ArrayList<>();

        for (Request request : requests) {
            result.add(returnRequestDto(request));
        }
        return result;
    }
}