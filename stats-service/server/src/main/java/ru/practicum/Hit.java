package ru.practicum;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hits", schema = "public")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Hit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Long id;

    @Column(name = "app", nullable = false)
    String app;

    @Column(name = "uri", nullable = false)
    String uri;

    @Column(name = "ip", nullable = false)
    String ip;

    @Column(name = "time_stamp", nullable = false)
    LocalDateTime timestamp;
}
