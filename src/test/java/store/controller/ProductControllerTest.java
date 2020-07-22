package store.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import store.ProductController;
import store.dto.ProductDTO;
import store.service.ProductService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;
    private ProductDTO mockProduct;

    @Before()
    public void setUp() {
        mockProduct = new ProductDTO("Test Product", 100, "Test Description");
        mockProduct.setId(1L);
    }


    @Test
    public void testAddProduct() {
        //when
        Mockito.when(productService.addProduct(Mockito.any(ProductDTO.class))).thenReturn(mockProduct);
        //then
        ProductDTO resultProduct = productController.addProduct(mockProduct);
        assertEquals(resultProduct, mockProduct);
    }

    @Test
    public void testUpdatePrice() {
        //given
        ProductDTO updateProduct = new ProductDTO();
        updateProduct.setId(1L);
        updateProduct.setPrice(100L);
        //when
        Mockito.when(productService.updatePrice(Mockito.any(ProductDTO.class))).thenReturn(mockProduct);
        //then
        ProductDTO resultProduct = productController.updatePrice(updateProduct);
        assertEquals(resultProduct, mockProduct);
    }
}
