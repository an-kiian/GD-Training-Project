package store.service;

import store.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    public ProductDTO getProductById(Long id);

    public List<ProductDTO> getProductByName(String nameProduct);

    public ProductDTO updatePrice(ProductDTO productDTO);

    public ProductDTO addProduct(ProductDTO productDTO);

    public List<ProductDTO> getAllProducts();
}
