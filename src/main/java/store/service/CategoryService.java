package store.service;

import store.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO getCategoryByName(String name);

    CategoryDTO addCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> getAllCategories();
}
