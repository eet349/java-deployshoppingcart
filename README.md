# Project Deploy Shopping Cart

A Student that completes this project shows they can:

* Switch between H2 and PostgreSQL databases
* Deploy to a cloud service

## Introduction

For this project, we are starting with your ending project
from [Shopping Cart](https://github.com/LambdaSchool/java-shoppingcart.git). We are going to deploy this project to
Heroku.

A shopping cart is a common application so let's look at one. This Java Spring REST API application will provide
endpoints for clients to perform the various CRUD operations on data sets contained in the application's data. Access to
these endpoints will be secured using OAuth2 Authentication.

### Database layout

The table layout from your Shopping Cart Project should be

![Shopping Cart Database Layout](shoppingcartdb.png)

All tables contain the following auditing fields

* createdby - user name who created the row. Should default to SYSTEM
* createddate - date field when the row was created
* lastmodifiedby - user name who last changed data in the row. Should default to SYSTEM
* lastmodifieddate - date field when the data in the row was last changed

Table Relationships include

* Users is the driving table and can be thought of as "Customers".
* Users have a Many to Many relationship with Products. One user can have many products in their "shopping cart" while a
  product may appear in many different users "shopping cart".
* A shopping cart is the collection of relationships between a user and product and is modeled using the join table
  CartItems which contains the quantity of the product being ordered.
* Users have a Many to Many relationship with Roles. A user can have many roles while many users can have the same role.

To find out the endpoints available to you in the initial application, you will need to use the Swagger document.
Remember the Swagger documentation can be access
at [http://localhost:2019/swagger-ui.html](http://localhost:2019/swagger-ui.html) once the application is running.

## Instructions

* [ ] Please fork and clone this repository.
* [ ] This repository does not have a starter project, so you must start with the Shopping Cart Application from your
  submission for the module
  project [https://github.com/LambdaSchool/java-shoppingcart.git](https://github.com/LambdaSchool/java-shoppingcart.git)
  . Regularly commit and push your code as appropriate.
* [ ] For the seed files provided in the original project, that all of the users' passwords are "LambdaLlama".

### MVP

* [ ] Configure the application so that you can switch between the H2 database and the PostgreSQL database via a
  property in the application.properties files.
* [ ] Required Testing.
    * [ ] Write at least 2 unit tests for the Cart Items service (without database use).
    * [ ] Write at least 2 unit tests for the Cart Items controller (without database use).
    * [ ] Write at least 2 integration tests for the Cart Items controller (with database use).
* [ ] Deploy the system to Heroku.
    * [ ] Make sure that your data remains stable after Heroku automatically restarts your application each night (turn
      off seed data once the seed data is loaded).

### Stretch Goal

* [ ] Create a file under main/resources called info.txt
    * [ ] In this file put the CURL commands needed for the following endpoints.
    * [ ] Include after each CURL command, its results.
    * [ ] The CURL commands should be run against your deployment on Heroku.
    * [ ] Routes for CURL command
        * [ ] GET /users/myinfo
        * [ ] GET /carts/user
        * [ ] DELETE /products/product/6
        * [ ] POST /products/product with this information:

```
    {
        "name": "RABBIT",
        "price": 300.0,
        "description": "A Great Pet",
        "comments": "Also known as a Bunny"
    }
```

* [ ] Extend the unit tests for Cart Items service to 100% coverage
* [ ] Extend the unit tests for Cart Items controller to 100% coverage
* [ ] Extend the integration tests for the Cart Items controller to 100% coverage