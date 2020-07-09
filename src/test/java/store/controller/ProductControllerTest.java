package store.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import store.model.Product;
import store.request.ProductRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Configuration
@ComponentScan("store.controller")
@EnableWebMvc
public class ProductControllerTest extends AbstractTest {
    @Before
    public void setUp() {
        super.setUp();
    }
//    @Test
//    public void getAllProduct() throws Exception {
//        String uri = "/store/product/getAll";
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
//                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        Product[] productlist = super.mapFromJson(content, Product[].class);
//        assertTrue(productlist.length > 0);
//    }
//
//    @Test
//    public void addProduct() throws Exception {
//        String uri = "/store/products/updatePrice";
//        Product product2 = new Product(22,"Pants","Bad Pants");
//        String inputJson = super.mapToJson(product2);
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(inputJson)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        assertEquals(content, "Product is added successsfully");
//    }
}