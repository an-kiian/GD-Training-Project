package store.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;


@Data
@NoArgsConstructor
public class ReviewDTO {
    @Min(value = 1, message = "id must be higher or equal 1")
    private Long id;
    @NotBlank(message = "Review text must be written")
    private String text;
    @Range(min = 0, max = 10, message = "Rating value must be between 0 to 10")
    private double rating;
    @Min(value = 1, message = "id_product must be higher or equal 1")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id_product;

    public ReviewDTO(Long id_product, double rating, String text) {
        this.id_product = id_product;
        this.rating = rating;
        this.text = text;
    }
}
