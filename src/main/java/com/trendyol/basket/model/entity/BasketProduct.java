package com.trendyol.basket.model.entity;

import com.trendyol.basket.model.dto.BasketProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasketProduct {

    private String productId;

    private String imageURL;

    private String productTitle;

    private BigDecimal productPrice;

    private int quantity;

    public BasketProduct(BasketProductDTO basketProductDTO) {
        // TODO: validations
        this.setProductId(basketProductDTO.getProductId());
        this.setImageURL(basketProductDTO.getImageURL());
        this.setProductTitle(basketProductDTO.getProductTitle());
        this.setProductPrice(basketProductDTO.getProductPrice());
    }
}
