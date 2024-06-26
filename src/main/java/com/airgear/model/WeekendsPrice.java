package com.airgear.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeekendsPrice {

    @Column(name = "weekends_price_amount")
    private BigDecimal weekendsPriceAmount;

    @Column(name = "weekends_price_currency")
    @Enumerated(EnumType.STRING)
    private Currency weekendsPriceCurrency;

    @Column(name = "weekends_price_type")
    @Enumerated(EnumType.STRING)
    private PriceType weekendsPriceType;
}
