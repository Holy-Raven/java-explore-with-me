package ru.practicum;

import ru.practicum.dto.HitDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static ru.practicum.Util.FORMATTER;

public class HitMapper {

    public static HitDto returnHitDto(Hit hit) {

        HitDto hitDto = HitDto.builder()
                .id(hit.getId())
                .app(hit.getApp())
                .uri(hit.getUri())
                .ip(hit.getIp())
                .timestamp(hit.getTimestamp().toString())
                .build();
        return hitDto;
    }

    public static Hit returnHit(HitDto hitDto) {

        Hit hit = Hit.builder()
                .id(hitDto.getId())
                .app(hitDto.getApp())
                .uri(hitDto.getUri())
                .ip(hitDto.getIp())
                .timestamp(LocalDateTime.parse(hitDto.getTimestamp(),FORMATTER))
                .build();
        return hit;
    }

    public static List<HitDto> returnHitDtoList(Iterable<Hit> hits) {
        List<HitDto> result = new ArrayList<>();

        for (Hit hit : hits) {
            result.add(returnHitDto(hit));
        }
        return result;
    }
}
