package store.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import store.model.Sale;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleRepository extends CrudRepository<Sale, Long> {

    Sale findById(Long id);

    List<Sale> findByDateOffAfterAndDateOnBefore(LocalDateTime dateOn, LocalDateTime dateOff);

    @Query(value = "select s from Sale s join s.categories cat where cat in :categories group by s.id")
    List<Sale> findByCategories(@Param("categories") List<String> categories);

}
