package com.trendyol.basket.service;

import com.trendyol.basket.exception.BasketNotFoundException;
import com.trendyol.basket.messageBroker.RabbitMQProducer;
import com.trendyol.basket.messageBroker.messages.CustomerCreatedMessage;
import com.trendyol.basket.messageBroker.messages.ProductPriceChangeMessage;
import com.trendyol.basket.messageBroker.messages.ProductStockChangeMessage;
import com.trendyol.basket.model.EmailMessage;
import com.trendyol.basket.model.entity.Basket;
import com.trendyol.basket.model.entity.BasketProduct;
import com.trendyol.basket.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@RequiredArgsConstructor
public class BasketService implements IBasketService {

    // This constant values can be get from config file or other strategies
    private static final int STOCK_EMAIL_LOWER_LIMIT = 3;

    private static final String STOCK_EMAIL_BODY_TEXT = "The stock of the product: %s is %s";

    private static final String OUT_OF_STOCK_EMAIL_BODY_TEXT = "The product: %s is out of stock!";

    private static final String PRODUCT_PRICE_DROPPED_EMAIL_BODY_TEXT = "The product: %s price is dropped from %s to %s";

    private final BasketRepository basketRepository;

    private final EmailService emailService;

    private final RabbitMQProducer producer;

    /**
     * When a user sign up to the system her/his basket is created with her/his user id.
     *
     * @param customerCreatedMessage used to create static one basket as long as user exists
     * @return basket
     */
    public Basket createBasket(CustomerCreatedMessage customerCreatedMessage) {
        return Basket.builder()
                .userId(customerCreatedMessage.getUserId())
                .userEmail(customerCreatedMessage.getUserEmail())
                .build();
    }

    @Transactional
    public Basket addProductToBasket(String userId, BasketProduct basketProduct) {
        Basket basket = getBasket(userId);
        basket.addProductToBasket(basketProduct);
        basket.updateBasketInfo();
        return basketRepository.save(basket);
    }

    @Transactional
    public Basket removeProductFromBasket(String userId, BasketProduct basketProduct) {
        Basket basket = getBasket(userId);
        basket.removeProductFromBasket(basketProduct);
        basket.updateBasketInfo();
        return basketRepository.save(basket);
    }

    public Basket getBasket(String userId) {
        return basketRepository.findById(userId).orElseThrow(BasketNotFoundException::new);
    }

    public void checkoutBasket(String userId) {
        // TODO: business validations can be done before checkout
        Basket basket = this.getBasket(userId);
        producer.send(basket);
    }

    public void handleStockChange(ProductStockChangeMessage productStockChangeMessage) {
        String emailBodyText = "";

        if (productStockChangeMessage.getQuantity() < STOCK_EMAIL_LOWER_LIMIT) {
            emailBodyText = String.format(STOCK_EMAIL_BODY_TEXT, productStockChangeMessage.getProductId(), productStockChangeMessage.getQuantity());
        } else if (productStockChangeMessage.getQuantity() == 0) {
            emailBodyText = OUT_OF_STOCK_EMAIL_BODY_TEXT;
        }

        // TODO: update baskets with new stock values
        // and additional business logic can be added later, e.g. when basket.product.stock < came.product.stock remove
        // item or show product to customer but decrease the basket total price
        if (!emailBodyText.isEmpty()) {
            String finalEmailBodyText = emailBodyText;
            basketRepository.findAll().forEach(basket -> {
                if (basket.getProducts().containsKey(productStockChangeMessage.getProductId())) {
                    emailService.sendEmail(EmailMessage.builder().emailBodyText(finalEmailBodyText).build(), basket.getUserEmail());
                }
            });
        }
    }

    public void handlePriceChange(ProductPriceChangeMessage productPriceChangeMessage) {
        basketRepository.findAll().forEach(basket ->
                basket.getProducts().computeIfPresent(productPriceChangeMessage.getProductId(), (id, product) -> {
                    if (product.getProductPrice().compareTo(productPriceChangeMessage.getPrice()) < 0) {
                        product.setProductPrice(productPriceChangeMessage.getPrice());
                        // TODO: instead of calculate all price all over again, just subtract the difference
                        basket.updateBasketProduct(product);
                        String emailText = String.format(PRODUCT_PRICE_DROPPED_EMAIL_BODY_TEXT, product.getProductId(), product.getProductPrice(), productPriceChangeMessage.getPrice());
                        emailService.sendEmail(EmailMessage.builder().emailBodyText(emailText).build(), basket.getUserEmail());
                    }
                    return product;
                })
        );
    }

}

