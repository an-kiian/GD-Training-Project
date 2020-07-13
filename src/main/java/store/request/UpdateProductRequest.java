package store.request;

import lombok.Data;

@Data
public class UpdateProductRequest {
    private Long idProduct;
    private double price;

}
