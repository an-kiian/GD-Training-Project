package store.repository;

import store.model.Category;
import store.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    List<Category> findByName(String name);
    //   Category findByIdCategory(Long idCategory);
}
