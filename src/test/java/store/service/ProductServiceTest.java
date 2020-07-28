//package store.service;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//import store.dto.ProductDTO;
//import store.mapper.EntityMapper;
//import store.model.Product;
//import store.repository.ProductRepository;
//import store.service.impl.ProductServiceImpl;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class ProductServiceTest {
//
//    @InjectMocks
//    private ProductServiceImpl service;
//
//    @Mock
//    private ProductRepository repository;
//
//    private Product product;
//
//    private ProductDTO productDTOFromMapper;
//
//    private EntityMapper<Product, ProductDTO> mapper;
//
//    List<Product> list;
//
//    private static final Long ID = 1L;
//    private static final String NAME = "name1";
//    private static final String DESCRIPTION = "desc1";
//
//    @Before
//    public void setUp() {
//        //given
//        product = new Product();
//        product.setId(ID);
//        product.setName(NAME);
//        product.setPrice(10);
//        product.setDescription(DESCRIPTION);
//        list = Collections.singletonList(product);
//
//        //given productDTO from mapper
//        mapper = (EntityMapper<Product, ProductDTO>) EntityMapper.getInstance();
//        productDTOFromMapper = mapper.toDTO(product, ProductDTO.class);
//    }
//
//    @Test
//    public void testGetById() {
//        //when
//        when(repository.findById(ID)).thenReturn(product);
//
//        //then
//        ProductDTO resultProductDTO = service.getProductById(ID);
//
//        //test the method is calling
//        verify(repository).findById(ID);
//
//        //checking correct data for productDTO
//        assertEquals(product, mapper.toEntity(resultProductDTO, Product.class));
//        //checking correct data for mapper
//        assertEquals(productDTOFromMapper, resultProductDTO);
//    }
//
//    @Test
//    public void testGetByName() {
//        //when
//        when(repository.findByName(NAME)).thenReturn(list);
//
//        //then
//        List<ProductDTO> resultList = service.getProductByName(NAME);
//        //convert list of productDTO to list of product
//        List<Product> resultProductList = resultList.stream().map(prodDTO -> mapper.toEntity(prodDTO, Product.class)).collect(Collectors.toList());
//
//        //test the method is calling
//        verify(repository).findByName(NAME);
//
//        //checking correct data
//        assertEquals(1, resultList.size());
//        assertEquals(list, resultProductList);
//
//    }
//
//    @Test
//    public void testGetAll() {
//        //given
//        Product newProduct = new Product();
//        newProduct.setId(2L);
//        newProduct.setName("name2");
//        newProduct.setPrice(20);
//        newProduct.setDescription("desc2");
//
//        List<Product> newList = Arrays.asList(product, newProduct);
//
//        //when
//        when(repository.findAll()).thenReturn(newList);
//
//        //then
//        List<ProductDTO> resultList = service.getAllProducts();
//        //convert list of productDTO to list of product
//        List<Product> resultProductList = resultList.stream().map(prodDTO -> mapper.toEntity(prodDTO, Product.class)).collect(Collectors.toList());
//
//        //test the method is calling
//        verify(repository).findAll();
//
//        //checking correct data
//        assertEquals(newList.size(), resultProductList.size());
//        assertEquals(2, resultProductList.size());
//        assertEquals(newList, resultProductList);
//    }
//
//    @Test
//    public void testAdd() {
//        //given
//        Product addedProduct = new Product();
//        addedProduct.setId(3L);
//        addedProduct.setName("name3");
//        addedProduct.setPrice(30);
//        addedProduct.setDescription("desc3");
//
//        //when
//        when(repository.save(addedProduct)).thenReturn(addedProduct);
//
//        //then
//        ProductDTO resultAddedProductDTO = service.addProduct(mapper.toDTO(addedProduct, ProductDTO.class));
//
//        //test the method is calling
//        verify(repository).save(addedProduct);
//        //checking correct data
//        assertEquals(addedProduct, mapper.toEntity(resultAddedProductDTO, Product.class));
//    }
//
//    @Test
//    public void testUpdatePrice() {
//        //given
//        Product updateProduct = new Product();
//        updateProduct.setId(ID);
//        updateProduct.setPrice(101);
//        updateProduct.setName(NAME);
//        updateProduct.setDescription(DESCRIPTION);
//
//        //when
//        when(repository.save(updateProduct)).thenReturn(product);
//        when(repository.findById(ID)).thenReturn(product);
//
//        //then
//        ProductDTO resultUpdateProduct = service.updatePrice(mapper.toDTO(updateProduct, ProductDTO.class));
//
//        //testing the method is calling
//        verify(repository).save(updateProduct);
//
//        //checking correct data
//        assertEquals(updateProduct, mapper.toEntity(resultUpdateProduct, Product.class));
//    }
//
//    @Test
//    public void testUpdatePriceForIncorrectId() {
//        //given
//        Product updateProduct = product;
//        updateProduct.setId(5L);
//
//        //when
//        when(repository.save(updateProduct)).thenReturn(product);
//
//        // then
//        ProductDTO nullProduct = service.updatePrice(mapper.toDTO(updateProduct, ProductDTO.class));
//
//        //checking correct data
//        assertNull(nullProduct);
//    }
//}
