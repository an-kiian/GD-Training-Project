package store.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import store.ProductController;
import store.model.Product;
import store.service.ProductService;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
    private MockMvc mockMvc;
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;

    private static List<Product> productList;


    @Before()
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        productList = new ArrayList<>();
        productList.add(new Product(1, "Product 1", "Description 1"));
        productList.add(new Product(2, "Product 2", "Description 2"));
    }

    @Test
    public void shouldGetAllProducts() throws Exception {
        Mockito.when(productService.getAllProduct()).thenReturn(productList);
        assertEquals(productController.getAllProduct().size(), 2);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/store/product/getAll").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = productList.toString();
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void shouldGetProductById() throws Exception {
        Mockito.when(productService.getProductById(1L)).thenReturn(productList.get(0));
        Product resultProduct = productController.getProductById(1L);
        assertEquals(resultProduct.getName(), "Product 1");
        Mockito.when(
                productService.getProductById(1L)).thenReturn(productList.get(0));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/store/product/getById/1").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println("RESUUUUULT:" + result.getResponse().getContentAsString());
        JSONAssert.assertEquals(productList.get(0).toString(), result.getResponse()
                .getContentAsString(), false);
    }


    @Test
    public void shouldGetProductByName() throws Exception {
        List<Product> testList = new ArrayList<>();
        testList.add(productList.get(1));
        Mockito.when(productService.getProductByName("Product 2")).thenReturn(testList);
        List<Product> resultList = productController.getProductByName("Product 2");
        assertEquals(resultList.size(), 1);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/store/product/getByName/Product 2").accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        JSONAssert.assertEquals(testList.toString(), result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void shouldAddProduct() throws Exception {
        String exampleProductJson = "{\"idProduct\":1,\"name\":\"Name\",\"price\":1.0,\"description\":\"description\"}";
        Product mockProduct = new Product(1, "Add Name", "Add Description");
        Mockito.when(productService.addProduct(Mockito.any())).thenReturn(mockProduct);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/store/product/addProduct")
                .accept(MediaType.APPLICATION_JSON).content(exampleProductJson)
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

    }

    @Test
    public void shouldUpdatePrice() throws Exception {
        String exampleUpdateJson = "{\"idProduct\":1,\"price\":1.0}";
        Product mockProduct = new Product(5, "Update Name", "Udpate Description");
        Mockito.when(productService.updatePrice(Mockito.any())).thenReturn(mockProduct);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/store/product/updatePrice")
                .accept(MediaType.APPLICATION_JSON).content(exampleUpdateJson)
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }
}
