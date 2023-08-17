package ru.practicum.compilation.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.compilation.CompilationService;
import ru.practicum.compilation.dto.CompilationDto;
import ru.practicum.compilation.dto.CompilationNewDto;
import ru.practicum.compilation.dto.CompilationUpdateDto;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/compilations")
public class CompilationAdminController {

    private final CompilationService compilationService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CompilationDto addCompilation(@Valid @RequestBody CompilationNewDto compilationNewDto) {

        log.info("Add Compilation {} ", compilationNewDto.getTitle());
        return compilationService.addCompilation(compilationNewDto);
    }

    @DeleteMapping("/{compId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCompilation(@PathVariable("compId") Long compId) {

        log.info("Delete Compilation {} ", compId);
        compilationService.deleteCompilation(compId);
    }

    @PatchMapping("/{compId}")
    @ResponseStatus(value = HttpStatus.OK)
    public CompilationDto updateCompilation(@Valid @RequestBody CompilationUpdateDto compilationUpdateDto,
                                            @PathVariable Long compId) {

        log.info("Update Compilation {} ", compId);
        return compilationService.updateCompilation(compId, compilationUpdateDto);
    }
}
