package com.trendyol.basket.service;

import com.trendyol.basket.repository.BasketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IBasketServiceTest {

    @InjectMocks
    private BasketService basketService;

    @Mock
    private BasketRepository basketRepository;

    @Test
    void whenAddBasketProductToBasket_ThenUpdateBasketInfo() {

        // TODO

        // Given


        // When


        // Then

    }


    // TODO: other unit tests of service layer
}
