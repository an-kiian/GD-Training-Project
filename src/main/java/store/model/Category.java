package store.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_category", nullable = false)
    private Long idCategory;
    @Column(nullable = false)
    private String name;

    // @ManyToMany(fetch = FetchType.EAGER, mappedBy = "categories")
    //private Set<Product> products;
    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

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
}
