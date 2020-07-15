package store.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long idProduct;
    private String name;
    private double price;
    private String description;

    public ProductDTO() {
    }

    public ProductDTO(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
