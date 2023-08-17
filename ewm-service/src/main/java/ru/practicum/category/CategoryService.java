package ru.practicum.category;

import ru.practicum.category.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto addCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);

    void deleteCategory(Long categoryId);

    List<CategoryDto> getCategories(Integer from, Integer size);

    CategoryDto getCategoryById(Long categoryId);
}
