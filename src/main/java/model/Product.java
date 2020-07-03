package model;

import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProduct;
    private double price;
    private String nameProduct;
    private String description;
public Product(){}
public Product(long price,String name,String description){
    this.price=price;
    this.nameProduct=name;
    this.description=description;

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
}
