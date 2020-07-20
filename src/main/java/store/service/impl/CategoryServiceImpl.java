package store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.dto.CategoryDTO;
import store.mapper.EntityMapper;
import store.model.Category;
import store.repository.CategoryRepository;
import store.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private EntityMapper<Category, CategoryDTO> mapper = new EntityMapper<>();

    @Autowired
    CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return mapper.toDTO(categoryRepository.findByName(name), CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> categoriesDTO = new ArrayList<>();
        categoryRepository.findAll().forEach(category -> categoriesDTO.add(mapper.toDTO(category, CategoryDTO.class)));
        return categoriesDTO;
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = mapper.toEntity(categoryDTO, Category.class);
        return mapper.toDTO(categoryRepository.save(category), CategoryDTO.class);
    }

}

