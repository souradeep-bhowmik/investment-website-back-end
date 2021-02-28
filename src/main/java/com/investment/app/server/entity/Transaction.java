package com.investment.app.server.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String user;

    @Getter
    @Setter
    private String product;

    @Getter
    @Setter
    private Date date;

    @Getter
    @Setter
    private Double size;

    @Getter
    @Setter
    private Double price;

    @Getter
    @Setter
    private Double fees;

    @Override
    public String toString() {
        return "Transaction [date=" + date + ", fees=" + fees + ", id=" + id + ", price=" + price + ", product="
                + product + ", size=" + size + ", user=" + user + "]";
    }

    public Transaction(String product, Date date, Double size, Double price, Double fees) {
        this.product = product;
        this.date = date;
        this.size = size;
        this.price = price;
        this.fees = fees;
    }

}
