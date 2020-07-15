package store.service;

import store.dto.ProductDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ProductService {

    ProductDTO getProductById(Long id);

    List<ProductDTO> getProductByName(String nameProduct);

    ProductDTO updatePrice(ProductDTO productDTO);

    ProductDTO addProduct(ProductDTO productDTO);

    List<ProductDTO> getAllProducts();
}
