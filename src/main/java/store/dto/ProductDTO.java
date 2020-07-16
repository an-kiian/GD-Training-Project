package store.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private double price;
    private String description;

    public ProductDTO(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
