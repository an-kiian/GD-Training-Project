package controller;

import model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;
import repository.ProductRepository;
import request.ProductRequest;
import request.UpdateProductRequest;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getProductById() throws Exception {
        mockMvc.perform(get("/store/product/getById").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); // 200 expected
    }
    @Test
    public void getProductByField() throws Exception {
        mockMvc.perform(get("//store/product/getByName").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); // 200 expected
    }

    @Test
    public void updatePrice() throws Exception {
        mockMvc.perform(put("/store/product/getById").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); // 200 expected
    }

    @Test
    public void addProduct() throws Exception {
        mockMvc.perform(get("/store/product/addProduct").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); // 200 expected
    }
    @Test
    public void updatePrice2() throws Exception {
        mockMvc.perform(get("/store/product/updatePrice2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); // 200 expected
    }
}
