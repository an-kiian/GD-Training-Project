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
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;
    private static final ProductDTO mockProduct=new ProductDTO("Test Product", 100, "Test Description");

    @Test
    public void testGetProducts() {
        //given
        List<ProductDTO> products = new ArrayList<>();
        products.add(mockProduct);
        //when
        Mockito.when(productService.getProducts(Mockito.anyList())).thenReturn(products);
        //then
        List<ProductDTO> resultList = productController.getProducts(Arrays.asList(new String[]{"Category 1"}));
        assertEquals(resultList, products);
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
        //when
        Mockito.when(productService.updatePrice(Mockito.any(ProductDTO.class))).thenReturn(mockProduct);
        //then
        ProductDTO resultProduct = productController.updatePrice(mockProduct);
        assertEquals(resultProduct, mockProduct);
    }
}
