package com.trendyol.basket.messageBroker.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckoutBasketConfig {

    @Value("${rabbitmq.queue}")
    private String queueName;

    @Value("${rabbitmq.routingKey}")
    private String routingKey;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Bean
    public Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding checkoutBasketBinding(final Queue queue, final DirectExchange directExchange) {
        return BindingBuilder.bind(queue)
                .to(directExchange)
                .with(routingKey);
    }
}
