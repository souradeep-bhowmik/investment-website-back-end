package com.investment.app.server.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class TransactionRequest {

    @Getter
    @Setter
    private String user;

    @Getter
    @Setter
    @JsonProperty(value = "product", required = true)
    private String product;

    @Getter
    @Setter
    @JsonProperty(value = "date", required = true)
    private Date date;

    @Getter
    @Setter
    @JsonProperty(value = "size", required = true)
    private Double size;

    @Getter
    @Setter
    @JsonProperty(value = "price", required = true)
    private Double price;

    @Getter
    @Setter
    @JsonProperty(value = "", required = true)
    private Double fees;

}
