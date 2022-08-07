# Getting Started

## Guides
The following guides illustrate how to use some features concretely:

## Add the prefix localhost:8080/api/delivery to make API calls!

### POST /resolve-address
#### example request body of single line address:
```
{   
    "searchTerm": "200 W 53rd St, New York, NY 10019, United States" 
}
```
### POST /timeslot
#### example request body of formatted address:
```
{   
    "address": {"street": "West 53rd Street",
    "line1": "200 West 53rd Street",
    "line2": "New York, NY 10019, United States of America",
    "country": "United States",
    "postcode": "10019"
    }
}
```
### POST /deliveries
#### example request body of booking delivery:
```
{
    "user": {
        "id": "drop@gmail.com",
        "name": "ben"
    },
    "timeslotId": 1
}
```
### POST /deliveries/{deliveryId}/complete
#### example uri for marking delivery as complete
```
localhost:8080/api/delivery/deliveries/1/complete
```
### DELETE /deliveries/{deliveryId}
#### example uri for canceling delivery
```
localhost:8080/api/delivery/deliveries/1
```
### GET /deliveries/daily
#### will get all today's deliveries

