package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Review {
    @Id
    @GeneratedValue(generator = "increment")
    private Long idReview;
    private String text;
    private double rating;
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
