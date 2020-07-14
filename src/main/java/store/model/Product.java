package store.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_product", nullable = false)
    private Long idProduct;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "description", nullable = false)
    private String description;

    public Product() {
    }

    public Product(long price, String name, String description) {
        this.price = price;
        this.name = name;
        this.description = description;
    }
}
