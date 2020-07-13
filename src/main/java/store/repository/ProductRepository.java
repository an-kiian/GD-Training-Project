package store.repository;

import store.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    public List<Product> findByName(String name);

    public List<Product> findByDescription(String description);

    public Product findByIdProduct(Long idProduct);
}
