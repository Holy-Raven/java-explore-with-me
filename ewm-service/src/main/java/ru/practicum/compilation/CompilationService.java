package ru.practicum.compilation;

import ru.practicum.compilation.dto.CompilationDto;
import ru.practicum.compilation.dto.CompilationNewDto;
import ru.practicum.compilation.dto.CompilationUpdateDto;

import java.util.List;

public interface CompilationService {

    CompilationDto addCompilation(CompilationNewDto compilationNewDto);

    void deleteCompilation(Long compId);

    CompilationDto updateCompilation(Long compId, CompilationUpdateDto compilationUpdateDto);

    List<CompilationDto> getCompilations(Boolean pinned, Integer from, Integer size);

    CompilationDto getCompilationById(Long compId);
}
