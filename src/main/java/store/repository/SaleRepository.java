package store.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import store.model.Sale;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SaleRepository extends CrudRepository<Sale,  Long> {

    Optional<Sale> findById(Long id);

    List<Sale> findByDateOffAfterAndDateOnBefore(LocalDate dateNow, LocalDate dateNow2);
}
