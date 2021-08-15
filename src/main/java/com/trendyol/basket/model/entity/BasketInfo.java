package com.trendyol.basket.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * This class used to hold the price informations of the basket
 * Assumption: The currency is only Turkish lira for the all prices
 *
 * @author sunal
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BasketInfo {

    // can be read from configuration file or by other strategies
    private static final BigDecimal SHIPPING_LOWER_LIMIT = new BigDecimal(100);

    /**
     * holds shipping price
     */
    // This constant value can be read from configuration file or by other strategies
    private final BigDecimal shippingCost = new BigDecimal("4.99");

    /**
     * holds sum of all products in basket
     */
    private BigDecimal totalProductPrice = new BigDecimal("0");;

    /**
     * holds sum of all campaigns discounts
     */
    private BigDecimal totalCampaignPrice = new BigDecimal("0");;

    /**
     * totalPrice = totalCampaignPrice + totalProductPrice + shippingCost
     */
    private BigDecimal totalPrice = new BigDecimal("0");;

    public void calculateTotalPrice() {
        this.totalPrice = this.totalProductPrice.add(this.totalCampaignPrice);
        addShippingCost();
    }

    public void addShippingCost() {
        if (this.totalPrice.compareTo(SHIPPING_LOWER_LIMIT) < 0) {
            this.totalPrice = this.totalPrice.add(this.shippingCost);
        }
    }
}
