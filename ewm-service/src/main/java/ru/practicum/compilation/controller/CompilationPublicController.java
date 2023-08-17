package ru.practicum.compilation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.compilation.CompilationService;
import ru.practicum.compilation.dto.CompilationDto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/compilations")
public class CompilationPublicController {

    private final CompilationService compilationService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CompilationDto> getCompilations(@RequestParam(defaultValue = "false", name = "pinned") Boolean pinned,
                                                @PositiveOrZero @RequestParam(name = "from", defaultValue = "0") Integer from,
                                                @Positive @RequestParam(name = "size", defaultValue = "10") Integer size) {

        log.info("Get all compilations from pinned = {}, and from = {}, size = {}", pinned,  from, size);
        return compilationService.getCompilations(pinned, from, size);
    }

    @GetMapping("/{compId}")
    @ResponseStatus(value = HttpStatus.OK)
    public CompilationDto getCompilationById(@PathVariable Long compId) {

        log.info("Get Compilation id {}", compId);
        return compilationService.getCompilationById(compId);
    }
}
