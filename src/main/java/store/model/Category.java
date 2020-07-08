package store.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_category",nullable = false)
    private Long idCategory;
    private String name;
   // @ManyToMany(fetch = FetchType.EAGER, mappedBy = "categories")
    //private Set<Product> products;
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

    //public Set<Product> getProducts() {
     //   return products;
   // }

    //public void setProducts(Set<Product> products) {
     //   this.products = products;
   // }
}
