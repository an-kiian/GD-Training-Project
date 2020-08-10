package store.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import store.service.impl.ProductServiceImpl;

import java.util.List;

@RestController
public class CategoryController {
    @Value("#{'${categories.list}'.split(',')}")
    List<String> categories;
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @GetMapping("/store/category")
    public List<String> getAll() {
        LOGGER.info("CategoryController: get All categories");
        return categories;
    }
}
