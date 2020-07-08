package store.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_product",nullable = false)
    private Long idProduct;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
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


public Product(){}
public Product(long id,long price,String name,String description,Set<Category>categories){
    this.idProduct=id;
    this.price=price;
    this.name=name;
    this.description=description;
   // this.categories=categories;

}
    public Long getIdProduct() {
        return idProduct;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setNameProduct(String nameProduct) {
        this.name = nameProduct;
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
    this.idProduct=id;
    }
}
