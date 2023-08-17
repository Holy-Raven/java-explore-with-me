package ru.practicum.category;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.event.EventRepository;
import ru.practicum.exception.ConflictException;
import ru.practicum.util.UnionService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final EventRepository eventRepository;
    private final UnionService unionService;

    @Transactional
    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {

        Category category = CategoryMapper.returnCategory(categoryDto);
        categoryRepository.save(category);

        return CategoryMapper.returnCategoryDto(category);
    }

    @Transactional
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {

        Category category = unionService.getCategoryOrNotFound(categoryId);
        category.setName(categoryDto.getName());
        categoryRepository.save(category);

        return CategoryMapper.returnCategoryDto(category);
    }

    @Transactional
    @Override
    public void deleteCategory(Long categoryId) {

        unionService.getCategoryOrNotFound(categoryId);

        if (!eventRepository.findByCategoryId(categoryId).isEmpty()) {
            throw new ConflictException(String.format("This category id %s is used and cannot be deleted", categoryId));
        }

        categoryRepository.deleteById(categoryId);
    }

    @Override
    public List<CategoryDto> getCategories(Integer from, Integer size) {

        PageRequest pageRequest = PageRequest.of(from / size, size);

        return CategoryMapper.returnCategoryDtoList(categoryRepository.findAll(pageRequest));
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {

        return CategoryMapper.returnCategoryDto(unionService.getCategoryOrNotFound(categoryId));
    }
}