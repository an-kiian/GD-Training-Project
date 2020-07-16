package store.mapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import store.dto.ProductDTO;
import store.model.Product;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class EntityMapperTest {
    private EntityMapper<Product, ProductDTO> mapper;
    private Product product;
    private ProductDTO productDTO;

    @Before
    public void setUp() {
        this.mapper = new EntityMapper<>();
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
        ProductDTO resultDTO = mapper.toDTO(product, ProductDTO.class);
        assertEquals(resultDTO.getName(), product.getName());
        assertEquals(resultDTO.getDescription(), product.getDescription());
        assertEquals(resultDTO.getPrice(), product.getPrice(), 0);
        assertEquals(resultDTO.getId(), product.getId());
    }

    @Test
    public void testToEntity() {
        Product resultProduct = mapper.toEntity(productDTO, Product.class);
        assertEquals(resultProduct.getName(), productDTO.getName());
        assertEquals(resultProduct.getDescription(), productDTO.getDescription());
        assertEquals(resultProduct.getPrice(), productDTO.getPrice(), 0);
        assertEquals(resultProduct.getId(), productDTO.getId());
    }
}
