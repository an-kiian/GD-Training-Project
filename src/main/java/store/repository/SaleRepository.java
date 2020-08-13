package store.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import store.dto.SaleDTO;
import store.model.QSale;
import store.model.Sale;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SaleRepository extends CustomRepository<Sale, QSale, Long> {

    @Override
    default void customize(QuerydslBindings querydslBindings, QSale root) {

    }

    Sale findById(Long id);

//    @Query(value = "select s from Sale s " +
//            "join s.categories cat " +
//            "where " +
//            "cat in (:categories) " +
//            "and " +
//            "(:id is null or s.id = :id) " +
//            "and " +
//            "((:saleDate) is null or " +
//            "((:saleDate) is not null and (:saleDate) between s.dateOn and s.dateOff)) " +
//            "group by (s.id) having count(s.id) = :catCount"
//    )
//    List<Sale> findByIdOrDateOnAfterOrDateOffBeforeByCategories(
//            @Param("id") Long id,
//            @Param("saleDate") LocalDateTime saleDate,
//            @Param("categories") List<String> categories,
//            @Param("catCount") long catCount
//    );



//    List<Sale> findByIdOrDateOnAfterOrDateOffBeforeByCategories(
//            @Param("id") Long id,
//            @Param("saleDate") LocalDateTime saleDate,
//            @Param("categories") List<String> categories,
//            @Param("catCount") long catCount
//    );
//
//
//    @Query(value = "select s from Sale s " +
//            "where " +
//            "(:id is null or s.id = :id) " +
//            "and " +
//            "(:saleDate is null or :saleDate between s.dateOn and s.dateOff) " +
//            "group by (s.id) "
//    )
//    List<Sale> findByIdOrDateOnAfterOrDateOffBefore(
//            @Param("id") Long id,
//            @Param("saleDate") LocalDateTime saleDate
//    );
}
