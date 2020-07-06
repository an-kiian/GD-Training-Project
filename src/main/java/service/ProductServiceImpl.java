package service;

import repository.impl.ProductRepositoryImpl;
import model.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductRepositoryImpl productRepository;
@Override
    public Product getProductById(long productId) {
        return productRepository.getProfileById(productId);

    }
    @Override
    public List<Product> getProductByName(String nameProduct) {
        return productRepository.getProductByName(nameProduct);

    }
    public Product updatePrice(long idProduct, double price) {
        return productRepository.updatePrice(idProduct,price);
    }
    public Product addProduct(Product product) {
        return productRepository.addProduct(product);
    }
}
