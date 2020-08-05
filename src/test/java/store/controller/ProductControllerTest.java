package store.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
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
    private final ProductDTO PRODUCT = new ProductDTO("Test Product", 100, "Test Description");

    @Test
    public void testGetProducts() {
        //given
        List<ProductDTO> products = new ArrayList<>();
        products.add(PRODUCT);
        //when
        Mockito.when(productService.getProducts(Mockito.anyList(), Mockito.anyBoolean())).thenReturn(products);
        //then
        List<ProductDTO> resultList = productController.getProducts(Arrays.asList(new String[]{"Category 1"}), true);
        assertEquals(resultList, products);
    }

    @Test
    public void testAddProduct() {
        //when
        Mockito.when(productService.addProduct(Mockito.any(ProductDTO.class))).thenReturn(PRODUCT);
        //then
        ProductDTO resultProduct = productController.addProduct(PRODUCT);
        assertEquals(resultProduct, PRODUCT);
    }

    @Test
    public void testUpdatePrice() {
        //when
        Mockito.when(productService.updatePrice(Mockito.any(ProductDTO.class))).thenReturn(PRODUCT);
        //then
        ProductDTO resultProduct = productController.updatePrice(PRODUCT);
        assertEquals(resultProduct, PRODUCT);
    }
}
