package store.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import store.model.Sale;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleRepository extends CrudRepository<Sale, Long> {

    Sale findById(Long id);

//    List<Sale> findByDateOffAfterAndDateOnBefore(LocalDateTime dateOn, LocalDateTime dateOff);
//
//    @Query(value = "select s from Sale s join s.categories cat where cat in :categories group by s.id")
//    List<Sale> findByCategories(@Param("categories") List<String> categories);

    @Query(value = "select s from Sale s join s.categories cat where s.id is not null and s.id = :id or s.dateOn is not null and s.dateOn = :dateOn or s.dateOff is not null and s.dateOff = :dateOff or cat in :categories group by s.id")
    List<Sale> findByIdOrDateOnAfterOrDateOffBeforeOrCategories(@Param("id") Long id, @Param("dateOn") LocalDateTime dateOn, @Param("dateOff") LocalDateTime dateOff, @Param("categories") List<String> categories);

}
