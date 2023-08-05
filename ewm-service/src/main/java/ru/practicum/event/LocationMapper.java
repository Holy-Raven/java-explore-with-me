package ru.practicum.event;

import ru.practicum.event.dto.LocationDto;
import ru.practicum.event.model.Location;

public class LocationMapper {
    public static LocationDto returnLocationDto(Location location) {
        LocationDto locationDto = LocationDto.builder()
                .lat(location.getLat())
                .lon(location.getLon())
                .build();
        return locationDto;
    }

    public static Location returnLocation(LocationDto locationDto) {
        Location location = Location.builder()
                .lat(locationDto.getLat())
                .lon(locationDto.getLon())
                .build();
        return location;
    }
}