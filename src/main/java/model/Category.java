package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
//@Table(name = "Category", schema = "", catalog = "")
public class Category {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    private Long idCategory;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "categories")
    private Set<Product> products;
    public Category(){}
    public Category(String name){
    this.name=name;

}
//@Column(name = "id_category", nullable = false, insertable = true, updatable = true)
    public Long getIdCategory() {
        return idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "idCategory=" + idCategory +
                ", name='" + name + '\'' +
                '}';
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
