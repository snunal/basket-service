package com.trendyol.basket.service;

import com.trendyol.basket.messageBroker.messages.CustomerCreatedEvent;
import com.trendyol.basket.model.entity.Basket;
import com.trendyol.basket.model.entity.BasketProduct;

public interface IBasketService {

    Basket createBasket(CustomerCreatedEvent customerCreatedEvent);
    Basket addProductToBasket(String userId, BasketProduct basketProduct);
    Basket removeProductFromBasket(String userId, BasketProduct basketProduct);
    Basket getBasket(String userId);
    void checkoutBasket(String userId);
}
