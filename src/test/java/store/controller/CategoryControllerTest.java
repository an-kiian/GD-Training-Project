package store.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryController controller;

    @Mock
    @Value("#{'${categories.list}'.split(',')}")
    private List<String> categories;

    @Test
    public void testGetAll(){
        //when
        when(controller.getAll()).thenReturn(categories);

        //then
        List<String> resultList = categoryController.getAll();

        //checking correct data
        assertEquals(categories, resultList);
    }
}
