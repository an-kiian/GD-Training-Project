package store.repository;

import store.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    public List<Product> findByName(String name);

    public Product findByIdProduct(Long idProduct);
}
