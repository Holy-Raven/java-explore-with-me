package ru.practicum.request;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.request.dto.RequestDto;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}/requests")
public class RequestController {

    private final RequestService requestService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public RequestDto addRequest(@PathVariable Long userId,
                                 @RequestParam Long eventId) {

        log.info("User id {} added request for Event id {}.", userId, eventId);
        return requestService.addRequest(userId, eventId);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<RequestDto> getRequestsByUserId(@PathVariable Long userId) {

        log.info("Get all requests by user id{}.", userId);
        return requestService.getRequestsByUserId(userId);
    }

    @PatchMapping("/{requestId}/cancel")
    @ResponseStatus(value = HttpStatus.OK)
    public RequestDto cancelRequest(@PathVariable Long userId,
                                    @PathVariable Long requestId) {

        log.info("User id{} canceled request id{}.", userId, requestId);
        return requestService.cancelRequest(userId, requestId);
    }
}
