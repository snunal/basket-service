# Basket Service

* Due to time constraints, I only did the basket service part. Actually, I was planning to provide payment, customer, product, stock and price services in addition to basket service. For this reason I could not test my basket service e2e.
* I couldn't prepare unit tests either. (I wish I could TDD)
* MongoDB is used for baskets.
* RabbitMQ is used for the communication between the services.

### TODOs
* RabbitMQ was used due to lack of experience with Message Broker systems. Another broker system such as Kafka, could be used instead.
* Compare performance of NoSQL and SQL solutions especially for BasketProduct parts.
 