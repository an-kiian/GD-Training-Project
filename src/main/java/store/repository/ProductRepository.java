package store.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import store.model.Product;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {


    @Query(value = "SELECT DISTINCT new Product(p.id,p.name,p.price,p.description,p.rating,p.reviewNumber) FROM Product p JOIN p.categories cat WHERE (:ignoreCategories=true OR cat in :categories) ORDER BY p.rating DESC")
    List<Product> findByCategory(@Param("categories") List<String> categories, @Param("ignoreCategories") boolean ignoreCategories);

    @Query(value = "SELECT DISTINCT p FROM Product p JOIN p.categories cat WHERE (:ignoreCategories=true OR cat in :categories) ORDER BY p.rating DESC")
    List<Product> findByCategoryWithReviews(@Param("categories") List<String> categories, @Param("ignoreCategories") boolean ignoreCategories);

    Optional<Product> findById(Long id);


}
