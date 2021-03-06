package store.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import store.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query(value = "select p from Product p join p.categories cat where cat in :categories group by p.id having count(p.id) = :categoryCount")
    List<Product> findByCategory(@Param("categories") List<String> categories, @Param("categoryCount") long catCount);

    Product findById(Long id);
}
