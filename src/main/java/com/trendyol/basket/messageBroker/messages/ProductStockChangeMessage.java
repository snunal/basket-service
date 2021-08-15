package com.trendyol.basket.messageBroker.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductStockChangeMessage {

    private String productId;

    private int quantity;

}
