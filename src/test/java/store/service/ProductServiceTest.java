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
import store.exception.ProductNotFoundException;
import store.mapper.EntityMapper;
import store.model.Product;
import store.repository.ProductRepository;
import store.service.impl.ProductServiceImpl;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNull;
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
    private List<Product> productList;
    private EntityMapper<Product, ProductDTO> mapper;
    private static final Long ID = 1L;

    @Before
    public void setUp() {
        product = new Product();
        product.setId(ID);
        product.setName("Product name");
        product.setPrice(10);
        product.setDescription("Product description");
        productList = Collections.singletonList(product);
        mapper = (EntityMapper<Product, ProductDTO>) EntityMapper.getInstance();
    }

    @Test
    public void testGetProducts() {
        //when
        Mockito.when(repository.findByCategory(Mockito.anyList(), Mockito.anyBoolean())).thenReturn(productList);
        //then
        List<ProductDTO> resultList = service.getProducts(new ArrayList<>(), false);
        List<Product> resultProductList = resultList.stream().map(prodDTO -> mapper.toEntity(prodDTO, Product.class)).collect(Collectors.toList());
        assertEquals(productList, resultProductList);
    }

    @Test
    public void testGetProductsWithReviews() {
        //when
        Mockito.when(repository.findByCategoryWithReviews(Mockito.anyList(), Mockito.anyBoolean())).thenReturn(productList);
        //then
        List<ProductDTO> resultList = service.getProducts(new ArrayList<>(), true);
        List<Product> resultProductList = resultList.stream().map(prodDTO -> mapper.toEntity(prodDTO, Product.class)).collect(Collectors.toList());
        assertEquals(productList, resultProductList);
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

    @Test(expected = ProductNotFoundException.class)
    public void testUpdatePriceForIncorrectId() {
        //when
        when(repository.findById(ID)).thenReturn(null);
        // then
        ProductDTO nullProduct = service.updatePrice(mapper.toDTO(product, ProductDTO.class));
        assertNull(nullProduct);
    }

    public void testCheckProductAndUpdateRatingForCorrectId() {
        //given
        product.setRating(2.0);
        //when
        when(repository.findById(ID)).thenReturn(product);
        when(repository.save(Mockito.any(Product.class))).thenReturn(product);
        // then
        Product resultProduct = service.checkProductAndUpdateRating(ID, 1.0);
        assertEquals(resultProduct.getRating(), 1.5);

    }

    @Test(expected = ProductNotFoundException.class)
    public void testCheckProductAndUpdateRatingForIncorrectId() {
        //when
        when(repository.findById(ID)).thenReturn(null);
        // then
        Product resultProduct = service.checkProductAndUpdateRating(ID, 1.0);
        assertNull(resultProduct);
    }
}
