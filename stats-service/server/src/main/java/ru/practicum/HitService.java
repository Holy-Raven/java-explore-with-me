package ru.practicum;

import ru.practicum.dto.HitDto;
import ru.practicum.dto.StatsDto;

import java.util.List;

public interface HitService {

    void addHit(HitDto hitDto);

    List<StatsDto> getStats(String start, String end, List<String> uris, Boolean unique);
}
