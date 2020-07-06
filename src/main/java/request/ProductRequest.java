package request;

import model.Category;
import model.Review;

import javax.persistence.*;
import java.util.Set;

public class ProductRequest {
    private String nameProduct;
    private double price;
    private String description;
    private long[] categories;

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
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

    public long[] getCategories() {
        return categories;
    }

    public void setCategories(long[] categories) {
        this.categories = categories;
    }
}
