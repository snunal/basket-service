package com.trendyol.basket.messageBroker;

import com.trendyol.basket.messageBroker.messages.CustomerCreatedEvent;
import com.trendyol.basket.messageBroker.messages.ProductPriceChangeEvent;
import com.trendyol.basket.messageBroker.messages.ProductStockChangeEvent;
import com.trendyol.basket.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class RabbitMQListener {

    private final BasketService basketService;

    @RabbitListener(queues = "${rabbitmq.customer.queue}")
    public void createBasket(CustomerCreatedEvent customerCreatedEvent) {
        basketService.createBasket(customerCreatedEvent);
    }

    @RabbitListener(queues = "${rabbitmq.product.queue.stock}")
    public void handleStockChange(ProductStockChangeEvent productStockChangeEvent) {
        basketService.handleStockChange(productStockChangeEvent);
    }

    @RabbitListener(queues = "${rabbitmq.product.queue.price}")
    public void handlePriceChange(ProductPriceChangeEvent productPriceChangeEvent) {
        basketService.handlePriceChange(productPriceChangeEvent);
    }
}
