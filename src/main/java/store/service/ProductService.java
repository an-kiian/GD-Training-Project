package store.service;

import store.model.Product;
import store.request.ProductRequest;
import store.request.UpdateProductRequest;

public interface ProductService {
    public Product getProductById(Long id);
    public Iterable<Product> getProductByName(String nameProduct);
    public Product updatePrice(UpdateProductRequest updateRequest) ;
    public Product addProduct(ProductRequest productRequest) ;
}
