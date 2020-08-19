package store.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Embeddable
@Entity
@Table(name = "sale")
@Data
@NoArgsConstructor
public class Sale{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date_on", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate dateOn;

    @Column(name = "date_off", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate dateOff;

    @Column(name = "percent")
    private double percent;

    @ElementCollection
    @Column(name = "categories")
    private List<String> categories;
}
