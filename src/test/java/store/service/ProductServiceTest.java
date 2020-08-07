package store.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import store.dto.ProductDTO;
import store.mapper.EntityMapper;
import store.model.Product;
import store.model.Review;
import store.repository.ProductRepository;
import store.service.impl.ProductServiceImpl;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl service;
    @Mock
    private ProductRepository repository;

    private Product product;
    private List<Product> productList;
    private EntityMapper<Product, ProductDTO> mapper;

    private static final Long ID = 1L;

    @Before
    public void setUp() {
        //given
        product = new Product();
        product.setId(ID);
        product.setName("Product name");
        product.setPrice(10);
        product.setDescription("Product description");
        productList = Arrays.asList(product);
        mapper = (EntityMapper<Product, ProductDTO>) EntityMapper.getInstance();
    }

    @Test
    public void testGetProductsWithoutReviews() {

        //when
        Mockito.when(repository.findByCategory(Mockito.anyList(), Mockito.anyInt())).thenReturn(productList);
        //then
        List<ProductDTO> resultList = service.getProducts(new ArrayList<>(), false);
        List<Product> resultProductList = resultList.stream().map(prodDTO -> mapper.toEntity(prodDTO, Product.class)).collect(Collectors.toList());
        assertEquals(productList, resultProductList);
        assertNull(resultList.get(0).getReviews());
    }

    @Test
    public void testGetProductsWithReviews() {
        //given
        productList.get(0).setReviews(new HashSet<Review>());
        //when
        Mockito.when(repository.findByCategory(Mockito.anyList(), Mockito.anyInt())).thenReturn(productList);
        //then
        List<ProductDTO> resultList = service.getProducts(new ArrayList<>(), true);
        List<Product> resultProductList = resultList.stream().map(prodDTO -> mapper.toEntity(prodDTO, Product.class)).collect(Collectors.toList());
        assertEquals(productList, resultProductList);
        assertNotNull(resultList.get(0).getReviews());
    }

    @Test
    public void testAdd() {
        //when
        when(repository.save(Mockito.any(Product.class))).thenReturn(product);
        //then
        ProductDTO resultProductDto = service.addProduct(mapper.toDTO(product, ProductDTO.class));
        verify(repository).save(product);
        assertEquals(product, mapper.toEntity(resultProductDto, Product.class));
    }

    @Test
    public void testUpdatePrice() {
        //when
        when(repository.save(Mockito.any(Product.class))).thenReturn(product);
        when(repository.findById(ID)).thenReturn(product);
        //then
        ProductDTO resultUpdateProduct = service.updatePrice(mapper.toDTO(product, ProductDTO.class));
        verify(repository).save(product);
        assertEquals(product, mapper.toEntity(resultUpdateProduct, Product.class));
    }

    @Test
    public void testUpdatePriceForIncorrectId() {
        //when
        when(repository.save(Mockito.any(Product.class))).thenReturn(product);
        // then
        ProductDTO nullProduct = service.updatePrice(mapper.toDTO(product, ProductDTO.class));
        assertNull(nullProduct);
    }
}
