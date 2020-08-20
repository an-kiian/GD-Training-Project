package store.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import store.validator.CategoriesConstraint;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class SaleDTO {

    @Min(value = 1, message = "sale id must be higher or equal 1")
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOn;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOff;

    @Min(value = 0, message = "percent must not be negative")
    @Max(value = 100, message = "percent must not be higher then 100")
    private double percent;

    @CategoriesConstraint
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> categories;

    public SaleDTO(LocalDate dateOn, LocalDate dateOff, double percent, List<String> categories) {
        this.dateOn = dateOn;
        this.dateOff = dateOff;
        this.percent = percent;
        this.categories = categories;
    }
}
