package store.controller;

import org.junit.Before;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.junit.Assert.assertEquals;
@Configuration
@ComponentScan("store.config.controller")
@EnableWebMvc
public class ProductControllerTest extends AbstractTest {
    @Before
    public void setUp() {
        super.setUp();
    }

//    @Test
//    public void getProductById() throws Exception {
//       mvc.perform(get("/store/product/getById").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()); //200 expected
//
//        int status = mvcResult.getResponse().getStatus();
//       assertEquals(200, status);
//
//    }
//    @Test
//    public void getProductByField() throws Exception
//    {
//        mockMvc.perform( MockMvcRequestBuilders
//                .get("/store/product/getByName")
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.products").exists())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.products[*].productId").isNotEmpty());
//    }
//    @Test
//    public void updatePrice() throws Exception {
//        String uri = "/store/product/updatePrice";
//        MvcResult mvcResult =  mvc.perform(get("/form"))
//                .andExpect(status().isOk());
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        //  String content = mvcResult.getResponse().getContentAsString();
//
//    }
//    @Test
//    public void addProduct() throws Exception {
//        String uri = "/store/product/addProduct";
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
//                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//
//    }

}