package service;

import model.Product;
import request.ProductRequest;
import request.UpdateProductRequest;

import java.util.List;

public interface ProductService {
    public Product getProductById(long productId);
    public Product getProductByName(String nameProduct);
    public Product updatePrice(UpdateProductRequest updateRequest) ;
    public Product updatePrice2(long id,long price) ;
    public Product addProduct(ProductRequest productRequest) ;
}
