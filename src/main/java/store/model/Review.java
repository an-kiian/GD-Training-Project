package store.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_review", nullable = false)
    private Long id;
    @Column(name = "text")
    private String text;
    @Column(name = "rating")
    private double rating;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Review that = (Review) o;
        return (Objects.equals(text, that.text) || Objects.equals(rating, that.rating) || Objects.equals(id, that.id));
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(text) + Objects.hash(rating) + Objects.hash(id);
        return result;
    }
}
