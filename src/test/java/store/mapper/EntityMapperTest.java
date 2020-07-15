package store.mapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import store.dto.ProductDTO;
import store.model.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;

@RunWith(MockitoJUnitRunner.class)
public class EntityMapperTest {
    @Mock
    private EntityMapper<Product, ProductDTO> mapper;
    private Product product;
    private ProductDTO productDTO;

    @Before
    public void setUp() {
        product = new Product();
        product.setPrice(100);
        product.setDescription("Product Description");
        product.setName("Product Name");
        product.setId(1L);
        productDTO = new ProductDTO("Product Name", 100, "Product Description");
        productDTO.setId(1L);
    }

    @Test
    public void testToDTO() {
        //given
        Mockito.when(mapper.toDTO(Mockito.any(Product.class), eq(ProductDTO.class))).thenReturn(productDTO);
        //when
        ProductDTO resultDTO = mapper.toDTO(product, ProductDTO.class);
        //then
        assertEquals(resultDTO.getName(), product.getName());
        assertEquals(resultDTO.getDescription(), product.getDescription());
        assertEquals(resultDTO.getPrice(), product.getPrice(), 0);
        assertEquals(resultDTO.getId(), product.getId());
    }

    @Test
    public void testToEntity() {
        //given
        Mockito.when(mapper.toEntity(Mockito.any(ProductDTO.class), eq(Product.class))).thenReturn(product);
        //when
        Product resultProduct = mapper.toEntity(productDTO, Product.class);
        //then
        assertEquals(resultProduct.getName(), productDTO.getName());
        assertEquals(resultProduct.getDescription(), productDTO.getDescription());
        assertEquals(resultProduct.getPrice(), productDTO.getPrice(), 0);
        assertEquals(resultProduct.getId(), productDTO.getId());
    }
}
