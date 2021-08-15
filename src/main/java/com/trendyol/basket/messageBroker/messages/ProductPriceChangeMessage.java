package com.trendyol.basket.messageBroker.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductPriceChangeMessage {

    private String productId;

    private BigDecimal price;
}
