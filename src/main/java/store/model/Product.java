package store.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
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
//    @ManyToMany
//    @JoinTable(name="Product_Category",
//            joinColumns = @JoinColumn(name="id_product", referencedColumnName="id_product"),
//            inverseJoinColumns = @JoinColumn(name="id_category", referencedColumnName="id_category")
//    )
//    private Set<Category> categories;
//
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
//    private Set<Review> reviews;


    public Product() {
    }

    public Product(Long id, long price, String name, String description, Set<Category> categories) {
        this.idProduct = id;
        this.price = price;
        this.name = name;
        this.description = description;
        // this.categories=categories;

    }

    public Product(long price, String name, String description) {
        this.price = price;
        this.name = name;
        this.description = description;
    }


    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    //   public Set<Category> getCategories() {
//        return categories;
//    }
//
//    public void setCategories(Set<Category> categories) {
//        this.categories = categories;
//    }

    public void setId(Long id) {
        this.idProduct = id;
    }
}
