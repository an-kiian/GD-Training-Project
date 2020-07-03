package Repositories;

import Repositories.interfaces.ProductRepository;
import model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class ProductRepositoryImpl implements ProductRepository {

    //TODO
    public Product getProfileById(long personId) {
        return null;
    }
    //TODO
    public List<Product> getProfileById(String nameProduct) {
        return null;
    }
    //TODO
    public Product updatePrice(long idProduct, double price) {
        return null;
    }
}
