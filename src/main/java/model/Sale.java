package model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Sale {
    @Id
    @GeneratedValue(generator = "increment")
    @Column(name="id_sale",nullable = false)
    private Long idSale;
    @Column(nullable = false)
    private LocalDate date_on;
    @Column(nullable = false)
    private LocalDate date_off;
    @Column(nullable = false)
    private double percent;
public Sale(){}
public Sale(String date_on, String date_off,double percent){
    this.percent=percent;
    this.date_on=LocalDate.parse(date_on);
    this.date_off=LocalDate.parse(date_off);
}

    public Long getIdSale() {
        return idSale;
    }

    public LocalDate getDate_on() {
        return date_on;
    }

    public void setDate_on(LocalDate date_on) {
        this.date_on = date_on;
    }

    public LocalDate getDate_off() {
        return date_off;
    }

    public void setDate_off(LocalDate date_off) {
        this.date_off = date_off;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "idSale=" + idSale +
                ", date_on=" + date_on +
                ", date_off=" + date_off +
                ", percent=" + percent +
                '}';
    }
}
