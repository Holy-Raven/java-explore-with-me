package ru.practicum.category;

import ru.practicum.category.dto.CategoryDto;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {
    public static CategoryDto returnCategoryDto(Category category) {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
        return categoryDto;
    }

    public static Category returnCategory(CategoryDto categoryDto) {
        Category category = Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();
        return category;
    }

    public static List<CategoryDto> returnCategoryDtoList(Iterable<Category> categories) {
        List<CategoryDto> result = new ArrayList<>();

        for (Category category : categories) {
            result.add(returnCategoryDto(category));
        }
        return result;
    }
}
