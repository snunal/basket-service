package com.trendyol.basket.messageBroker;

import com.trendyol.basket.messageBroker.messages.CustomerCreatedMessage;
import com.trendyol.basket.messageBroker.messages.ProductPriceChangeMessage;
import com.trendyol.basket.messageBroker.messages.ProductStockChangeMessage;
import com.trendyol.basket.service.BasketService;
import com.trendyol.basket.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class RabbitMQListener {

    private final BasketService basketService;

    @RabbitListener(queues = "${rabbitmq.customer.queue}")
    public void createBasket(CustomerCreatedMessage customerCreatedMessage) {
        basketService.createBasket(customerCreatedMessage);
    }

    @RabbitListener(queues = "${rabbitmq.product.queue.stock}")
    public void handleStockChange(ProductStockChangeMessage productStockChangeMessage) {
        basketService.handleStockChange(productStockChangeMessage);
    }

    @RabbitListener(queues = "${rabbitmq.product.queue.price}")
    public void handlePriceChange(ProductPriceChangeMessage productPriceChangeMessage) {
        basketService.handlePriceChange(productPriceChangeMessage);
    }
}
