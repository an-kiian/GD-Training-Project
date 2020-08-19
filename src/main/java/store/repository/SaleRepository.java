package store.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import store.model.Sale;

import java.util.Optional;

@Repository
public interface SaleRepository extends CrudRepository<Sale,  Long> {

    Optional<Sale> findById(Long id);
}
