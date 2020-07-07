package repository;

import model.Category;
import model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Product, Long> {
    List<Category> findByName(String name);
    Category findById(long id);
}
