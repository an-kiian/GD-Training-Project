package store.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import store.validator.CategoriesConstraint;

import java.util.List;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
public class ProductDTO {
    @Min(value = 1, message = "product id must be higher or equal 1")
    private Long id;
    private String name;
    @Min(value = 0, message = "price must not be negative")
    private double price;
    private String description;
    @CategoriesConstraint
    private List<String> categories;

    public ProductDTO(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
