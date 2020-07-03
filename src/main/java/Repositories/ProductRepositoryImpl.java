package Repositories;

import model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class ProductRepository extends JpaRepository<Product, Long> {


    @Override
    public List<Product> findAll(Sort sort) {
        return null;
    }
}
