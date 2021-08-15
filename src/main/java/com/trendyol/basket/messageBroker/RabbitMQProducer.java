package com.trendyol.basket.messageBroker;

import com.trendyol.basket.model.entity.Basket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQProducer {

    private final RabbitTemplate template;

    @Value("${rabbitmq.routingKey}")
    private String routingName;

    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    public void send(Basket basket) {
        // TODO: the basket can be send after put into a wrapper class
        template.convertAndSend(exchangeName, routingName, basket);
        log.info("Sent message: '{}'", basket);
    }

}
