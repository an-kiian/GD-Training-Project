package store.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Value("#{'${categories.list}'.split(',')}")
    List<String> categories;

    @GetMapping("/store/category")
    public List<String> getAll() {
        return categories;
    }
}
