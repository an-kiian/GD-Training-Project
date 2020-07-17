package store.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import store.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findById(Long id);
}
