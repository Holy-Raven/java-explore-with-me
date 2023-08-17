package ru.practicum.category;

import lombok.experimental.UtilityClass;
import ru.practicum.category.dto.CategoryDto;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class CategoryMapper {
    public CategoryDto returnCategoryDto(Category category) {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
        return categoryDto;
    }

    public Category returnCategory(CategoryDto categoryDto) {
        Category category = Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();
        return category;
    }

    public List<CategoryDto> returnCategoryDtoList(Iterable<Category> categories) {
        List<CategoryDto> result = new ArrayList<>();

        for (Category category : categories) {
            result.add(returnCategoryDto(category));
        }
        return result;
    }
}