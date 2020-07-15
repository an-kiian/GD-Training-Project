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
    private MockMvc mockMvc;
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;
    private static ProductDTO mockProduct;

    @Before()
    public void setUp() throws JsonProcessingException {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        mockProduct = new ProductDTO("Test Product", 100, "Test Description");
        mockProduct.setIdProduct(1L);
    }

    @Test
    public void testGetAllProducts() throws Exception {
        List<ProductDTO> productList = new ArrayList<>();
        productList.add(mockProduct);
        productList.add(new ProductDTO("Test Product 2", 200, "Test Description 2"));
        Mockito.when(productService.getAllProducts()).thenReturn(productList);
        List<ProductDTO> resultList = productService.getAllProducts();
        Mockito.verify(productService, Mockito.times(1)).getAllProducts();
        assertEquals(resultList.size(), 2);
        assertThat(resultList.get(0).getName()).isEqualTo(productList.get(0).getName());
        assertThat(resultList.get(1).getName()).isEqualTo(productList.get(1).getName());
    }

    @Test
    public void testGetProductById() throws Exception {
        Mockito.when(productService.getProductById(Mockito.anyLong())).thenReturn(mockProduct);
        ProductDTO resultProduct = productService.getProductById(1L);
        Mockito.verify(productService, Mockito.times(1)).getProductById(1l);
        assertThat(mockProduct.getDescription()).isEqualTo(resultProduct.getDescription());
        assertThat(mockProduct.getName()).isEqualTo(resultProduct.getName());
        assertThat(mockProduct.getPrice()).isEqualTo(resultProduct.getPrice());
        assertThat(mockProduct.getIdProduct()).isEqualTo(resultProduct.getIdProduct());
    }

    @Test
    public void testGetProductByName() throws Exception {
        List<ProductDTO> productList = new ArrayList<>();
        productList.add(mockProduct);
        Mockito.when(productService.getProductByName(Mockito.anyString())).thenReturn(productList);
        List<ProductDTO> resultList = productService.getProductByName("Test Product");
        Mockito.verify(productService, Mockito.times(1)).getProductByName("Test Product");
        assertEquals(resultList.size(), 1);
        assertThat(resultList.get(0).getName()).isEqualTo(mockProduct.getName());
    }

    @Test
    public void testAddProduct() throws Exception {
        Mockito.when(productService.addProduct(Mockito.any(ProductDTO.class))).thenReturn(mockProduct);
        ProductDTO resultProduct = productService.addProduct(mockProduct);
        Mockito.verify(productService, Mockito.times(1)).addProduct(mockProduct);
        assertThat(resultProduct.getPrice()).isEqualTo(mockProduct.getPrice());
        assertThat(resultProduct.getDescription()).isEqualTo(mockProduct.getDescription());
        assertThat(resultProduct.getName()).isEqualTo(mockProduct.getName());
    }

    @Test
    public void testUpdatePrice() throws Exception {
        ProductDTO updateProduct = new ProductDTO();
        updateProduct.setIdProduct(1L);
        updateProduct.setPrice(100L);
        Mockito.when(productService.updatePrice(Mockito.any(ProductDTO.class))).thenReturn(mockProduct);
        ProductDTO resultProduct = productService.updatePrice(updateProduct);
        Mockito.verify(productService, Mockito.times(1)).updatePrice(updateProduct);
        assertThat(resultProduct.getPrice()).isEqualTo(mockProduct.getPrice());
        assertThat(resultProduct.getIdProduct()).isEqualTo(mockProduct.getIdProduct());
        assertThat(resultProduct.getName()).isEqualTo(mockProduct.getName());
    }
}
