package com.trendyol.basket.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasketProductDTO {

    private String userId;

    private String productId;

    private String imageURL;

    private String productTitle;

    private BigDecimal productPrice;
}
