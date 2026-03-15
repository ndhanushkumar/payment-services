# Payment Service

Spring Boot WebFlux microservice for processing payments. Backed by MongoDB.

## Prerequisites
- Java 17+
- Maven
- MongoDB running on `localhost:27017`

## Setup & Run

```bash
git clone https://github.com/ndhanushkumar/payment-services.git
cd payment-services
./mvnw spring-boot:run
```

Runs on **http://localhost:7072**

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/payment-services/submit-payment` | Submit a payment |
| GET | `/payment-services/void-payment/{orderId}` | Void/refund a payment |

## Payment Types
| Code | Meaning | Card Details Required |
|---|---|---|
| `CR` | Credit card | ✅ cardNumber, expiryDate, CVV |
| `AC` | Account | ❌ |
| `ZD` | Digital wallet | ❌ |
| `BTA` | Business Travel Account | ❌ |

## Note
This service is called internally by `ordering-services`. You typically don't need to call it directly.
