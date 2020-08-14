package store.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.model.Review;
import store.validator.CategoriesConstraint;

import java.util.List;
import java.util.Set;

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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> categories;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<Review> reviews;
    private double rating;

    public ProductDTO(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
