package repository.impl;

import repository.ProductRepository;
import model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class ProductRepositoryImpl implements ProductRepository {

    //TODO
    public Product getProfileById(long idProduct) {
    return null;
    }
    //TODO
    public List<Product> getProductByName(String nameProduct) {
        return null;
    }
    //TODO
    public Product updatePrice(long idProduct, double price) {
        return null;
    }
    //TODO
    public Product addProduct(Product product) {
        return null;
    }
}
