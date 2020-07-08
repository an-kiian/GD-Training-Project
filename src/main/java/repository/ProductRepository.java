package repository;


import model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByName(String name);
    List<Product> findByDescription(String description);
   Product findById(long id);
   List<Product> findAll();
    @Query("select p from Product p where p.idProduct=?1")
   Product findProductWithPriceLower(Long id, Sort sort);
}
