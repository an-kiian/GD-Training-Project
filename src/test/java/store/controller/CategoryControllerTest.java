package store.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    private List<String> categories;

    @Before
    public void setUp(){
        //given
        categories = new ArrayList<>();
        categories.add("First Category");
        categories.add("Second Category");
        categories.add("Third Category");
        ReflectionTestUtils.setField(categoryController, "categories", categories);
    }

    @Test
    public void testGetAll(){
        //then
        List<String> resultList = categoryController.getAll();

        //checking correct data
        assertEquals(3, resultList.size());
        assertEquals(categories, resultList);
    }
}
