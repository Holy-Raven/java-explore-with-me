package ru.practicum.category.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.category.CategoryService;
import ru.practicum.category.dto.CategoryDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/categories")
public class CategoryAdminController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CategoryDto createCategory(@Valid @RequestBody CategoryDto categoryDto) {

        log.info("Add Category {} ", categoryDto.getName());
        return categoryService.addCategory(categoryDto);
    }

    @PatchMapping("/{catId}")
    @ResponseStatus(value = HttpStatus.OK)
    public CategoryDto updateCategory(@Positive @PathVariable("catId") Long categoryId,
                                      @Valid @RequestBody CategoryDto categoryDto) {

        log.info("Update Category {} ", categoryDto.getName());
        return categoryService.updateCategory(categoryDto, categoryId);
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCategory(@Positive @PathVariable("catId") Long categoryId) {

        log.info("Delete Category {} ", categoryId);
        categoryService.deleteCategory(categoryId);
    }
}