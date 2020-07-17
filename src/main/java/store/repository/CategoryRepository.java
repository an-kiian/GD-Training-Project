package store.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import store.model.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findById(Long id);

    List<Category> findByName(String name);
}
