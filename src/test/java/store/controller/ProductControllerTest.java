package store.controller;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.AdditionalMatchers.not;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import store.ProductController;
import store.dto.ProductDTO;
import store.service.ProductService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;
    private static ProductDTO mockProduct;

    @Before()
    public void setUp() {
        mockProduct = new ProductDTO("Test Product", 100, "Test Description");
        mockProduct.setId(1L);
    }

    @Test
    public void testGetAllProducts(){
        //given
        List<ProductDTO> productList = new ArrayList<>();
        productList.add(mockProduct);
        productList.add(new ProductDTO("Test Product 2", 200, "Test Description 2"));
        Mockito.when(productController.getAllProducts()).thenReturn(productList);
        //when
        List<ProductDTO> resultList = productController.getAllProducts();
        //then
        assertEquals(resultList.size(), 2);
        assertEquals(resultList.get(0).getName(),productList.get(0).getName());
        assertEquals(resultList.get(1).getName(),productList.get(1).getName());
    }

    @Test
    public void testGetProductById(){
        //given
        Mockito.when(productController.getProductById(Mockito.anyLong())).thenReturn(mockProduct);
        //when
        ProductDTO resultProduct = productController.getProductById(1L);
        //then
        assertEquals(mockProduct.getDescription(),resultProduct.getDescription());
        assertEquals(mockProduct.getName(),resultProduct.getName());
        assertEquals(mockProduct.getPrice(),resultProduct.getPrice(),0);
        assertEquals(mockProduct.getId(),resultProduct.getId());
    }

    @Test
    public void testGetProductByName(){
        //given
        List<ProductDTO> productList = new ArrayList<>();
        productList.add(mockProduct);
        Mockito.when(productController.getProductByName(Mockito.anyString())).thenReturn(productList);
        //when
        List<ProductDTO> resultList = productController.getProductByName("Test Product");
        //then
        assertEquals(resultList.size(), 1);
        assertEquals(resultList.get(0).getName(),mockProduct.getName());
    }

    @Test
    public void testAddProduct(){
        //given
        Mockito.when(productController.addProduct(Mockito.any(ProductDTO.class))).thenReturn(mockProduct);
        //when
        ProductDTO resultProduct = productController.addProduct(mockProduct);
        //then
        assertEquals(resultProduct.getPrice(),mockProduct.getPrice(),0);
        assertEquals(resultProduct.getDescription(),mockProduct.getDescription());
        assertEquals(resultProduct.getName(),mockProduct.getName());
    }

    @Test
    public void testUpdatePrice() {
        //given
        ProductDTO updateProduct = new ProductDTO();
        updateProduct.setId(1L);
        updateProduct.setPrice(100L);
        Mockito.when(productController.updatePrice(Mockito.any(ProductDTO.class))).thenReturn(mockProduct);
        //when
        ProductDTO resultProduct = productController.updatePrice(updateProduct);
        //then
        assertEquals(resultProduct.getPrice(),mockProduct.getPrice(),0);
        assertEquals(resultProduct.getId(),mockProduct.getId());
        assertEquals(resultProduct.getName(),mockProduct.getName());
    }
}
