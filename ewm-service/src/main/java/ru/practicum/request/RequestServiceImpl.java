package ru.practicum.request;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.event.EventRepository;
import ru.practicum.exception.ConflictException;
import ru.practicum.request.dto.RequestDto;
import ru.practicum.user.User;
import ru.practicum.event.model.Event;
import ru.practicum.util.enums.State;
import ru.practicum.util.enums.Status;
import java.time.LocalDateTime;
import ru.practicum.util.UnionService;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final EventRepository eventRepository;
    private final UnionService unionService;

    @Override
    @Transactional
    public RequestDto addRequest(Long userId, Long eventId) {

        User user = unionService.getUserOrNotFound(userId);
        Event event = unionService.getEventOrNotFound(eventId);

        if (event.getParticipantLimit() <= event.getConfirmedRequests() && event.getParticipantLimit() != 0) {
            throw new ConflictException(String.format("Event %s requests exceed the limit", event));
        }

        if (event.getInitiator().getId().equals(userId)) {
            throw new ConflictException(String.format("Initiator, user id %s cannot give a request to participate in his event", user.getId()));
        }

        if (requestRepository.findByRequesterIdAndEventId(userId, eventId).isPresent()) {
            throw new ConflictException(String.format("You have already applied to participate in Event %s", event.getTitle()));
        }

        if (event.getState() != State.PUBLISHED) {
            throw new ConflictException(String.format("Event %s has not been published, you cannot request participation", eventId));
        } else {

            Request request = Request.builder()
                    .requester(user)
                    .event(event)
                    .created(LocalDateTime.now())
                    .status(Status.PENDING)
                    .build();

            if (!event.getRequestModeration() || event.getParticipantLimit() == 0) {
                request.setStatus(Status.CONFIRMED);
                request = requestRepository.save(request);
                event.setConfirmedRequests(requestRepository.countAllByEventIdAndStatus(eventId, Status.CONFIRMED));
                eventRepository.save(event);

                return RequestMapper.returnRequestDto(request);
            }

            request = requestRepository.save(request);

            return RequestMapper.returnRequestDto(request);
        }
    }

    @Override
    public List<RequestDto> getRequestsByUserId(Long userId) {

        unionService.getUserOrNotFound(userId);
        List<Request> requestList = requestRepository.findByRequesterId(userId);

        return RequestMapper.returnRequestDtoList(requestList);
    }

    @Override
    @Transactional
    public RequestDto cancelRequest(Long userId, Long requestId) {

        unionService.getUserOrNotFound(userId);
        Request request = unionService.getRequestOrNotFound(requestId);
        request.setStatus(Status.CANCELED);

        return RequestMapper.returnRequestDto(requestRepository.save(request));
    }
}
