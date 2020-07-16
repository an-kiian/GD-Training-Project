package store.service;

import store.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO getProductById(Long id);

    List<ProductDTO> getProductByName(String nameProduct);

    ProductDTO updatePrice(ProductDTO productDTO);

    ProductDTO addProduct(ProductDTO productDTO);

    List<ProductDTO> getAllProducts();
}
