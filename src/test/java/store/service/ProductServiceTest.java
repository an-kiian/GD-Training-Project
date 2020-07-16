package store.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import store.dto.ProductDTO;
import store.model.Product;
import store.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl service;

    @Mock
    private ProductRepository repository;

    private Product product;

    @Before
    public void setUp() {
        product = new Product();
        product.setIdProduct(1L);
        product.setName("name1");
        product.setPrice(10);
        product.setDescription("desc1");
    }

    @Test
    public void testGetById() {
        //test behavior
        when(repository.findByIdProduct(1L)).thenReturn(product);
        ProductDTO productDTO = service.getProductById(1L);

        //test method call
        verify(repository).findByIdProduct(1L);

        //checking correct data
        assertEquals(product.getIdProduct(), productDTO.getIdProduct());
        assertEquals(product.getName(), productDTO.getName());
        assertEquals(product.getPrice(), productDTO.getPrice());
        assertEquals(product.getDescription(), productDTO.getDescription());
    }

    @Test
    public void testGetByName() {
        //test behavior
        List<Product> list = new ArrayList<>();
        list.add(product);
        when(repository.findByName("name1")).thenReturn(list);
        List<ProductDTO> resultList = service.getProductByName("name1");

        //test method call
        verify(repository).findByName("name1");

        //checking correct data
        assertEquals(1, resultList.size());
        assertEquals(product.getName(), resultList.get(0).getName());

    }

    @Test
    public void testGetAll(){
        //test behavior
        List<Product> list = new ArrayList<>();
        list.add(product);
        Product newProduct = new Product();
        newProduct.setIdProduct(2L);
        newProduct.setName("name2");
        newProduct.setPrice(20);
        newProduct.setDescription("desc2");
        list.add(newProduct);
        when(repository.findAll()).thenReturn(list);
        List<ProductDTO> resultList = service.getAllProducts();

        //test method call
        verify(repository).findAll();

        //checking correct data
        assertEquals(list.size(), resultList.size());
        assertEquals(2, resultList.size());
        assertEquals(list.get(0).getName(), resultList.get(0).getName());
        assertEquals(list.get(1).getName(), resultList.get(1).getName());
    }

    @Test
    public void testAdd(){
        //test behavior
        Product addedProduct = new Product();
        product.setIdProduct(3L);
        product.setName("name3");
        product.setPrice(30);
        product.setDescription("desc3");
        when(repository.save(addedProduct)).thenReturn(addedProduct);
        Product resultAddedProduct = repository.save(addedProduct);

        //test method call
        verify(repository).save(addedProduct);

        //checking correct data
        assertEquals(addedProduct.getName(), resultAddedProduct.getName());
        assertEquals(addedProduct.getPrice(), resultAddedProduct.getPrice());
        assertEquals(addedProduct.getDescription(), resultAddedProduct.getDescription());
    }

    @Test
    public void testUpdatePrice(){
        //test behavior
        Product updateProduct = new Product();
        updateProduct.setIdProduct(1L);
        updateProduct.setPrice(101);
        when(repository.save(updateProduct)).thenReturn(updateProduct);
        Product resultUpdatePrice = repository.save(updateProduct);

        //test method call
        verify(repository).save(updateProduct);

        //checking correct data
        assertEquals(updateProduct.getIdProduct(), resultUpdatePrice.getIdProduct());
        assertEquals(updateProduct.getPrice(), resultUpdatePrice.getPrice());
        assertEquals(updateProduct.getName(), resultUpdatePrice.getName());
    }
}
