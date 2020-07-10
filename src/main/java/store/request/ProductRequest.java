package store.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private String name;
    private double price;
    private String description;

//    public long[] getCategories() {
//        return categories;
//    }
//
//    public void setCategories(long[] categories) {
//        this.categories = categories;
//    }
}
