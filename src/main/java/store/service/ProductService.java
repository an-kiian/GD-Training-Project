package store.service;

import store.model.Product;
import store.request.ProductRequest;
import store.request.UpdateProductRequest;

import java.util.List;

public interface ProductService {
    public Product getProductById(Long id);

    public List<Product> getProductByName(String nameProduct);

    public Product updatePrice(UpdateProductRequest updateRequest);

    public Product addProduct(ProductRequest productRequest);

    public List<Product> getProductByDescription(String description);

    public Iterable<Product> getAllProduct();
}
