package com.investment.app.server.model;

import java.math.BigDecimal;
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
    private BigDecimal totalSellPrice;

    @Getter
    @Setter
    private BigDecimal originalCost;

    @Getter
    @Setter
    private BigDecimal profit;

    @Getter
    @Setter
    private String profitPercentage;

}
