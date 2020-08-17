package store.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import store.model.Sale;

@Repository
public interface SaleRepository extends CrudRepository<Sale,  Long> {

    Sale findById(Long id);
}
