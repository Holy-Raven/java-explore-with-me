package ru.practicum.event.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.category.Category;
import ru.practicum.user.User;
import ru.practicum.util.State;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events", schema = "public")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "initiator_id", nullable = false)
    User initiator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    Location location;

    @Column(name = "title")
    String title;

    @Size(min = 20, max = 2000)
    @Column(name = "annotation")
    String annotation;

    @Column(name = "event_date")
    LocalDateTime eventDate;

    @Column(name = "paid")
    Boolean paid;

    @Size(min = 20, max = 7000)
    @Column(name = "description")
    String description;

    @JoinColumn(name = "confirmed_requests")
    Long confirmedRequests;

    @Column(name = "participant_limit")
    Long participantLimit;

    @Column(name = "request_moderation")
    Boolean requestModeration;

    @Column(name = "published_on")
    LocalDateTime publishedOn;

    @Column(name = "created_on")
    LocalDateTime createdOn;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    State state;

    @Column(name = "views")
    Long views;
}