## Quickstart

1. Run the following command

```
docker-compose up --build
```

## Endpoints

- GET http://localhost:8001/ - retrieves inventory information for all products
- GET http://localhost:8002/ - retrieves all orders
- GET http://localhost:8002/{id} - retrieves an order by its id
- POST http://localhost:8002/ - places a new order

    ``` 
    {"Order": {
        "Items": [
        {
            "Product": 
            {
                "Id":"623b0cc3a34d25d8567f9f82", 
                "Color":
                {
                    "Code":"R"
                }
            },
            "Quantity": 2
        }], 
        "Id": "",
        "CreatedAt": "2021-10-10T15:04:05Z"
    },
    "ShippingAddress":"my address"}
    ```
- GET http://localhost:8003/ - retrieves shipping information for all orders
- GET http://localhost:8003/{id} - retrieves shipping information for the specified order
