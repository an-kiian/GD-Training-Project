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

    @Query(value = "select s from Sale s " +
            "join s.categories cat " +
            "where " +
            "(:id is null or " +
            "(:id is not null and s.id = :id)) " +
            "and " +
            "((:saleDate) is null or " +
            "((:saleDate) is not null and (:saleDate) between s.dateOn and s.dateOff)) " +
            "and " +
            "((:categories) is null or " +
            "((:categories) is not null and cat in (:categories)))" +
            "group by s.id")
    List<Sale> findByIdOrDateOnAfterOrDateOffBefore(@Param("id") Long id, @Param("saleDate") LocalDateTime saleDate, @Param("categories") List<String> categories);

//    @Query(value = "select s from Sale s join s.categories cat where cat in (:categories) group by s.id having count(s.id) = :categoryCount")
//    List<Sale> cat(@Param("id") Long id, @Param("saleDate") LocalDateTime saleDate, @Param("categories") List<String> categories, @Param("categoryCount") long categoryCount);
}
