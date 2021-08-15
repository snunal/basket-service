package com.trendyol.basket.controller;

import com.trendyol.basket.model.dto.BasketProductDTO;
import com.trendyol.basket.model.entity.Basket;
import com.trendyol.basket.model.entity.BasketProduct;
import com.trendyol.basket.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/basket")
@RestController
public class BasketController {

    private final BasketService basketService;

    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Basket> addProductToBasket(@Valid @RequestBody BasketProductDTO basketProductDto) {
        BasketProduct basketProduct = new BasketProduct(basketProductDto);
        return new ResponseEntity<>(basketService.addProductToBasket(basketProductDto.getUserId(), basketProduct), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Basket> deleteProductFromBasket(@Valid @RequestBody BasketProductDTO basketProductDto) {
        BasketProduct basketProduct = new BasketProduct(basketProductDto);
        return new ResponseEntity<>(basketService.removeProductFromBasket(basketProductDto.getUserId(), basketProduct), HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Basket> getBasket(@PathVariable String userId) {
        return new ResponseEntity<>(basketService.getBasket(userId), HttpStatus.OK);
    }

}
