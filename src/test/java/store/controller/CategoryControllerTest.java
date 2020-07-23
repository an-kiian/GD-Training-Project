package store.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Value("#{'${categories.list}'.split(',')}")
    private List<String> categories;

    @Test
    public void testGetAll(){
        //then
        List<String> resultList = categoryController.getAll();

        //checking correct data
        assertEquals(categories, resultList);
    }
}
