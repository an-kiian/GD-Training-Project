package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(generator = "increment")
    private Long idProduct;
    private double price;
    private String nameProduct;
    private String description;
    @ManyToMany
    @JoinTable(name="Product_Category",
            joinColumns = @JoinColumn(name="idProduct", referencedColumnName="id_product"),
            inverseJoinColumns = @JoinColumn(name="idCategory", referencedColumnName="id_category")
    )
    private Set<Category> categories;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    private Set<Review> reviews;
public Product(){}
public Product(long id,long price,String name,String description,Set<Category>categories){
    this.idProduct=id;
    this.price=price;
    this.nameProduct=name;
    this.description=description;
    this.categories=categories;

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

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", price=" + price +
                ", name='" + nameProduct + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void setId(String s) {
    }
}
