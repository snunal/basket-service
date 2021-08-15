# Basket Service

* Due to lack of experience in web development and lack of time, I could only do part of the basket service part in 2 days.
* Actually, I was planning to provide PAYMENT, CUSTOMER, PRODUCT, STOCK and PRICE services in addition to BASKET service and API-Gateway. For this reason I could not test my basket service e2e.
* I couldn't prepare unit tests either. (I wish I could TDD)
* MongoDB is used for baskets.
* RabbitMQ is used for the communication between the services.

---

### Future Work
* RabbitMQ was used due to lack of experience with Message Broker systems. Another broker system such as Kafka, could be used instead.
* Compare performance of NoSQL and SQL solutions especially for product list of baskets parts.
* Outbox pattern can be added for reliability and consistency between microservices.
 