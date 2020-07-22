package store.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import store.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query(value="select p from product p where :category MEMBER OF p.categories")
    List<Product> findByCategory(@Param("category")String category);

    Product findById(Long id);
}
