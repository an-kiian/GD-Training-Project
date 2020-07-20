package store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import store.dto.CategoryDTO;
import store.service.CategoryService;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/store/category/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/store/category/{name}")
    public List<CategoryDTO> getCategoryByName(@PathVariable String name){
        return categoryService.getCategoryByName(name);
    }

    @GetMapping("/store/category")
    public List<CategoryDTO> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping("/store/category")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO addCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.addCategory(categoryDTO);
    }
}
