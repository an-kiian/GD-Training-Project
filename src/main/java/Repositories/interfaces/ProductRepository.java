package Repositories.interfaces;

import model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findById(long personId);

    List<Product> findByName(String nameProduct);

    @Query("UPDATE Product SET Product.price= :price WHERE Product.idProduct = :id_prod")
    Stream<Product> updatePrice(@Param("id_prod") long idProduct, @Param("price") double price);

    Product save(Product product);
}
