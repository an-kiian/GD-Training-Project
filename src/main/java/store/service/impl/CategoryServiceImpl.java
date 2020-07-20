package store.service.impl;

import org.springframework.stereotype.Service;
import store.dto.CategoryDTO;
import store.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public CategoryDTO getCategoryById(Long id) {
        return null;
    }

    @Override
    public List<CategoryDTO> getCategoryByName(String name) {
        return null;
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return null;
    }
}
