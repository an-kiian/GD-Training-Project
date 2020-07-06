package model;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(generator = "increment")
    private Long idReview;
    private String text;
    private double rating;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;
public Review(){

}
public Review(String text, double rating){
    this.text=text;
    this.rating=rating;
}
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Long getIdReview() {
        return idReview;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Review{" +
                "idReview=" + idReview +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                '}';
    }
}
