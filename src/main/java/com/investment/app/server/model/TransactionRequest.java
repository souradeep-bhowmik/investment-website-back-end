package com.investment.app.server.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

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
    @JsonProperty(value = "fees", required = true)
    private Double fees;

}
