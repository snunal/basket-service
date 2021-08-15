package com.trendyol.basket.repository;

import com.trendyol.basket.model.entity.Basket;
import com.trendyol.basket.model.entity.BasketProduct;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends MongoRepository<Basket, String> {

    // TODO: convert get map list by key to below strategy
//    @Query("{'userSubscription.map':{$elemMatch: { k: ?0, v: true } } }")
//    List<Person> findByUserSubscription(String key);

}
