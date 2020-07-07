package repository;


import model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByName(String name);
   Product findById(long id);
   List<Product> findAll();
}
