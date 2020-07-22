package store.service;

import store.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO updatePrice(ProductDTO productDTO);

    ProductDTO addProduct(ProductDTO productDTO);

    List<ProductDTO> getProducts(List<String> categories);
}
