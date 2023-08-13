package ru.practicum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.dto.HitDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static ru.practicum.Util.DATE_FORMAT;

@Service
public class StatsClient extends BaseClient {

    public ResponseEntity<Object> addHit(HitDto hitDto) {
        return post("/hit", hitDto);
    }

    @Autowired
    public StatsClient(@Value("${stats-server.url}") String serverUrl, RestTemplateBuilder builder) {

        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                        .build()
        );
    }

    public ResponseEntity<Object> findStats(LocalDateTime start, LocalDateTime  end, String uris, boolean unique) {

        Map<String, Object> parameters = Map.of(
                "start", start.format(DateTimeFormatter.ofPattern(DATE_FORMAT)),
                "end", end.format(DateTimeFormatter.ofPattern(DATE_FORMAT)),
                "uris", uris,
                "unique", unique
        );
        return get("/stats?start={start}&end={end}&uris={uris}&unique={unique}", parameters);
    }
}
