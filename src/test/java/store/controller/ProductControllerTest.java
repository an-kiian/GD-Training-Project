package store.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
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
    public void testGetAllProducts() {
        //given
        List<ProductDTO> productList = new ArrayList<>();
        productList.add(mockProduct);
        productList.add(new ProductDTO("Test Product 2", 200, "Test Description 2"));
        //when
        Mockito.when(productService.getAllProducts()).thenReturn(productList);
        //then
        List<ProductDTO> resultList = productController.getAllProducts();
        assertEquals(resultList.size(), 2);
        assertEquals(resultList.get(0).getName(), productList.get(0).getName());
        assertEquals(resultList.get(1).getName(), productList.get(1).getName());
    }

    @Test
    public void testGetProductById() {
        //when
        Mockito.when(productService.getProductById(Mockito.anyLong())).thenReturn(mockProduct);
        //then
        ProductDTO resultProduct = productController.getProductById(1L);
        assertEquals(mockProduct.getDescription(), resultProduct.getDescription());
        assertEquals(mockProduct.getName(), resultProduct.getName());
        assertEquals(mockProduct.getPrice(), resultProduct.getPrice(), 0);
        assertEquals(mockProduct.getId(), resultProduct.getId());
    }

    @Test
    public void testGetProductByName() {
        //given
        List<ProductDTO> productList = new ArrayList<>();
        productList.add(mockProduct);
        //when
        Mockito.when(productService.getProductByName(Mockito.anyString())).thenReturn(productList);
        //then
        List<ProductDTO> resultList = productController.getProductByName("Test Product");
        assertEquals(resultList.size(), 1);
        assertEquals(resultList.get(0).getName(), mockProduct.getName());
    }

    @Test
    public void testAddProduct() {
        //when
        Mockito.when(productService.addProduct(Mockito.any(ProductDTO.class))).thenReturn(mockProduct);
        //then
        ProductDTO resultProduct = productController.addProduct(mockProduct);
        assertEquals(resultProduct.getPrice(), mockProduct.getPrice(), 0);
        assertEquals(resultProduct.getDescription(), mockProduct.getDescription());
        assertEquals(resultProduct.getName(), mockProduct.getName());
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
        assertEquals(resultProduct.getPrice(), mockProduct.getPrice(), 0);
        assertEquals(resultProduct.getId(), mockProduct.getId());
        assertEquals(resultProduct.getName(), mockProduct.getName());
    }
}
