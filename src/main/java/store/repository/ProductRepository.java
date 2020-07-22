package store.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import store.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query(value = "select * from product p where ?1 MEMBER OF p.categories", nativeQuery = true)
    List<Product> findByCategory(String category);

    Product findById(Long id);
}
