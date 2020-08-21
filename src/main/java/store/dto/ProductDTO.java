package store.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import store.validator.CategoriesConstraint;

import java.util.List;

@Data
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    @Range(min = 0)
    private double price;
    private Double salePrice;
    private String description;
    @CategoriesConstraint
    private List<String> categories;
    public ProductDTO(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
