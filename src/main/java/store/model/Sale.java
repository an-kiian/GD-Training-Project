package store.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.Config;
import com.querydsl.core.annotations.QueryEmbedded;
import com.querydsl.core.annotations.QueryInit;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Config(entityAccessors = true)
@Entity
@Table(name = "sale")
@Data
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date_on")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateOn;

    @Column(name = "date_off")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateOff;

    @Column(name = "percent")
    private double percent;

    @ElementCollection
    @Column(name = "categories")
    private List<String> categories;
}
