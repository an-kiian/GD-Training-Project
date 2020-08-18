package store.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.rules.ExpectedException;
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
    @Rule
    private final ExpectedException EXCEPTION = ExpectedException.none();
    private Product product;
    private List<Product> productList;
    private List<String> categories;
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
        categories = Collections.singletonList("First Category");
    }

    @Test
    public void testGetProducts() {
        //when
        Mockito.when(repository.findByCategory(categories, categories == null)).thenReturn(productList);
        //then
        List<ProductDTO> resultList = service.getProducts(categories, false);
        List<Product> resultProductList = resultList.stream().map(prodDTO -> mapper.toEntity(prodDTO, Product.class)).collect(Collectors.toList());
        assertEquals(productList, resultProductList);
    }

    @Test
    public void testGetProductsWithReviews() {
        //when
        Mockito.when(repository.findByCategoryWithReviews(categories, false)).thenReturn(productList);
        //then
        List<ProductDTO> resultList = service.getProducts(categories, true);
        List<Product> resultProductList = resultList.stream().map(prodDTO -> mapper.toEntity(prodDTO, Product.class)).collect(Collectors.toList());
        assertEquals(productList, resultProductList);
    }

    @Test
    public void testAdd() {
        //when
        when(repository.save(product)).thenReturn(product);
        //then
        ProductDTO resultProductDto = service.addProduct(mapper.toDTO(product, ProductDTO.class));
        verify(repository).save(product);
        assertEquals(product, mapper.toEntity(resultProductDto, Product.class));
    }

    @Test
    public void testUpdatePrice() {
        //when
        when(repository.save(product)).thenReturn(product);
        when(repository.findById(ID)).thenReturn(Optional.of(product));
        //then
        ProductDTO resultUpdateProduct = service.updatePrice(mapper.toDTO(product, ProductDTO.class));
        verify(repository).save(product);
        assertEquals(product, mapper.toEntity(resultUpdateProduct, Product.class));
    }

    @Test
    public void testUpdatePriceForIncorrectId() {
        //when
        when(repository.findById(ID)).thenThrow(new ProductNotFoundException(ID));
        // then
        EXCEPTION.expect(ProductNotFoundException.class);
        ProductDTO nullProduct = service.updatePrice(mapper.toDTO(product, ProductDTO.class));
        assertNull(nullProduct);
    }

    @Test
    public void testCheckProductAndUpdateRatingForCorrectId() {
        //given
        product.setRating(2.0);
        //when
        when(repository.findById(ID)).thenReturn(Optional.of(product));
        when(repository.save(product)).thenReturn(product);
        // then
        Product resultProduct = service.checkProductAndUpdateRating(ID, 1.0);
        assertEquals(resultProduct.getRating(), 1.5);

    }

    @Test
    public void testCheckProductAndUpdateRatingForIncorrectId() {
        //when
        when(repository.findById(ID)).thenThrow(new ProductNotFoundException(ID));
        // then
        EXCEPTION.expect(ProductNotFoundException.class);
        Product resultProduct = service.checkProductAndUpdateRating(ID, 1.0);
        assertNull(resultProduct);
    }
}
