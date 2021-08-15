package com.trendyol.basket.service;

import com.trendyol.basket.messageBroker.messages.CustomerCreatedMessage;
import com.trendyol.basket.model.entity.Basket;
import com.trendyol.basket.model.entity.BasketProduct;

public interface IBasketService {

    Basket createBasket(CustomerCreatedMessage customerCreatedMessage);
    Basket addProductToBasket(String userId, BasketProduct basketProduct);
    Basket removeProductFromBasket(String userId, BasketProduct basketProduct);
    Basket getBasket(String userId);
    void checkoutBasket(String userId);
}
