package com.investment.app.server.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostBasisResponse {

    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date sellDate;

    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date buyDate;

    @Getter
    @Setter
    private Double amountOfShare;

    @Getter
    @Setter
    private Double pricePerShare;

    @Getter
    @Setter
    private Double fees;

    @Getter
    @Setter
    private Double totalSellPrice;

    @Getter
    @Setter
    private Double originalCost;

    @Getter
    @Setter
    private Double profit;

    @Getter
    @Setter
    private Double profitPercentage;

}
