package store.controller;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import store.ProductController;
import store.model.Product;
import store.request.ProductRequest;
import store.request.UpdateProductRequest;
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

    private static Product mockProduct;
    private static String expectedJson;

    @Before()
    public void setUp() throws JsonProcessingException {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        mockProduct = new Product(100, "Test Product", "Test Description");
        expectedJson = mapToJson(mockProduct);
    }

    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> productList = new ArrayList<>();
        productList.add(mockProduct);
        Mockito.when(productService.getAllProduct()).thenReturn(productList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/store/product/getAll").accept(MediaType.APPLICATION_JSON);
        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();
        String resultJson = response.getContentAsString();
        assertThat(mapToJson(productList)).isEqualTo(resultJson);
    }

    @Test
    public void testGetProductById() throws Exception {
        Mockito.when(productService.getProductById(Mockito.anyLong())).thenReturn(mockProduct);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/store/product/getById/1").accept(
                MediaType.APPLICATION_JSON);
        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();
        String resultJson = response.getContentAsString();
        assertThat(expectedJson).isEqualTo(resultJson);
    }

    @Test
    public void testGetProductByName() throws Exception {
        List<Product> productList = new ArrayList<>();
        productList.add(mockProduct);
        Mockito.when(productService.getProductByName(Mockito.anyString())).thenReturn(productList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/store/product/getByName/Test Product").accept(
                MediaType.APPLICATION_JSON);
        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();
        String resultJson = response.getContentAsString();
        assertThat(mapToJson(productList)).isEqualTo(resultJson);
    }

    @Test
    public void testAddProduct() throws Exception {
        Mockito.when(productService.addProduct(Mockito.any(ProductRequest.class))).thenReturn(mockProduct);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/store/product/addProduct")
                .accept(MediaType.APPLICATION_JSON).content(expectedJson)
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();
        String resultJson = response.getContentAsString();
        assertThat(expectedJson).isEqualTo(resultJson);
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

    }

    @Test
    public void testUpdatePrice() throws Exception {
        Mockito.when(productService.updatePrice(Mockito.any(UpdateProductRequest.class))).thenReturn(mockProduct);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/store/product/updatePrice")
                .accept(MediaType.APPLICATION_JSON).content(expectedJson)
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();
        String resultJson = response.getContentAsString();
        assertThat(expectedJson).isEqualTo(resultJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}
