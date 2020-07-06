package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Category {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    private Long idCategory;
    private String name;
    public Category(){}
    public Category(String name){
    this.name=name;

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
