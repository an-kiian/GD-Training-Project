package store.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.hibernate.validator.constraints.Range;
import store.validator.CategoriesConstraint;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    @Range(min = 0)
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
