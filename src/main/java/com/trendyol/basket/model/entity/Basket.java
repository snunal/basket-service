package com.trendyol.basket.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "baskets")
public class Basket {

    /**
     * user has only 1 basket for this reason the userId can be used as basket id
     */
    @Id
    private String userId;

    private String userEmail;

    private Map<String, BasketProduct> products = new HashMap<>();

    private BasketInfo basketInfo = new BasketInfo();

    private List<Campaign> campaigns = new ArrayList<>();


    public void updateBasketInfo() {
        basketInfo.setTotalProductPrice(this.getTotalProductPrice());
        // TODO: campaigns
        basketInfo.calculateTotalPrice();
    }

    public void addProductToBasket(BasketProduct basketProduct) {
        products.computeIfPresent(basketProduct.getProductId(), (id, product) -> {
            product.setQuantity(product.getQuantity() + 1);
            return product;
        });
        products.putIfAbsent(basketProduct.getProductId(), basketProduct);
    }

    public void removeProductFromBasket(BasketProduct basketProduct) {
        BasketProduct _basketProduct = this.products.computeIfPresent(basketProduct.getProductId(), (id, product) -> {
            product.setQuantity(product.getQuantity() - 1);
            return product;
        });
        if (_basketProduct != null && _basketProduct.getQuantity() < 0) {
            this.products.remove(basketProduct.getProductId());
        }
    }

    public BigDecimal getTotalProductPrice() {
        BigDecimal totalProductPrice = new BigDecimal(0);
        for (BasketProduct basketProduct : this.products.values()) {
            totalProductPrice = totalProductPrice.add(basketProduct.getProductPrice().multiply(new BigDecimal(basketProduct.getQuantity())));
        }
        return totalProductPrice;
    }

    public void updateBasketProduct(BasketProduct basketProduct){
        products.replace(basketProduct.getProductId(), basketProduct);
        this.updateBasketInfo();
    }

}
