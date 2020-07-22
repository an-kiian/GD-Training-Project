package store.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import store.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByName(String name);

    @Query(value = "select * from Product p where p.categories = :categories", nativeQuery = true)
    List<Product> findByCategory(@Param("categories") List<String> categories);

    Product findById(Long id);
}
