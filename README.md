# Overview

GD-Training-Project is an implementation of the online store back-end functionality.
Checking the correctness of the developed functionality can be done via Postman.
The main functionality that is implemented in the project is working with a database, namely

Product management:

- adding a product;

- product price update;

- receiving products by categories from the database.

Review management:

- adding a new review;

- receiving reviews by product id.

## Summary

  - [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installing and Deployment](#installing-and-deployment)



## Getting Started

**Requirements:**
- OS: Windows, macOS, Linux
- JDK 8 and over
- Docker + MySql 8.0.15 (and over) container
- Maven 3.6.3 and over




For use and run application it is needed to clone the project to the local machine:

    git clone https://github.com/an-kiian/GD-Training-Project

## Prerequisites

**Maven**

For install maven you need to download package from the official [resource](https://maven.apache.org/download.cgi) and [install](https://mkyong.com/maven/how-to-install-maven-in-windows/) it.

**IntelliJ IDEA**

- download IntelliJ IDEA [Ultimate version](https://www.jetbrains.com/idea/promo/ultimate/?gclid=CjwKCAjwmMX4BRAAEiwA-zM4JogGjxxekfBMsDbtXyjyMEA_iB-_ii3aldVTHvjOrl7fOgTLPl3d8RoCVvUQAvD_BwE). 
- add Lombok plugin according to the [guideline](https://projectlombok.org/setup/intellij).
- add your username and password in `main/resources/application.properties` file of project :

      spring.datasource.username=[user_name]
      spring.datasource.password=[password]


**Postman**

Postman can be downloaded from the official [website](https://www.postman.com/downloads/)

**Docker + MySql container**

For successful build you need download from the official [website](https://docs.docker.com/get-docker/) , 
install and start  Docker application. 
Then for getting, deploy and execute MySql container inside Docker run in Terminal commands:

    docker pull mysql/mysql-server:[version_of_mysql]

    docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=root -d mysql:[version_of_mysql]

    docker exec -it mysql mysql -uroot -p

**Use password generated from Docker! You can see this in Docker Dashboard Logs.**

In MySql Terminal for access to database change your root password, use command:

    ALTER USER '[your_username]'@'localhost' IDENTIFIED BY '[newpassword]'

## Installing and Deployment
### Start service
Further launching of the project is possible in two ways: using the command line or using IntelliJ IDEA.

**Using command line**

To start the project from the command line, you need to run the following  commands sequence  from the .../GD-Training-Project/
    
    mvn clean
    mvn compile
    mvn spring-boot:run -Drun.arguments=--spring.datasource.username=[your_username], --spring.datasource.password=[your_password]
    
**Using IntelliJ IDEA**

For correct project start with IntelliJ IDEA you need to 
run class 

    /GD-Training-Project/src/main/java/store/Application.java

### Check service functionality
**Using Postman**

It is possible to use postman [collection](https://www.getpostman.com/collections/6820f2ae280f0001da40) for check service or make own requests.

*Product*

There are 3 requests types: *GET*, *POST* and *PUT* in the system.

1.GET:
- get product by categories: http://localhost:8080/store/product?categories={Category 1},{Category 2}, where {Category 1} and {Category 2} are categories according to which you want to get products.

- get product by categories with reviews: http://localhost:8080/store/product?categories={Category 1},{Category 2}&showReview=true, where {Category 1} and {Category 2} are categories according to which you want to get products.

If GET request is success, you will receive products in json format.

2.POST:
- add a new product: http://localhost:8080/store/product) with body in json format as:
    
    
    {"name":"Product name", "price":100, "description":"Product description, "categories":["First Category","Second Category"]}
   In case you set category which doesn't exist in the system or price lower than 0, you'll receive error and product won't be added to the database. If POST request is success, you will receive added product in json format.
    
3.PUT:
-update product price: http://localhost:8080/store/product/price with body in json format as:

    {"id":1, "price":200}
If PUT request is success and there is a product with specified id in the database, you will receive updated product in json format.
In case you set wrong product id or price lower than 0, you'll receive error and product won't be updated.

*Review*

There are 2 requests types: *GET* and *POST* in the system.

1.GET:
- get product by product id: http://localhost:8080/store/review/{idProduct}, where {idProduct} is a product id which reviews you want to receive. 

If GET request is success, you will receive products in json format.

2.POST:
- add a new review: http://localhost:8080/store/review) with body in json format as:
    
    
    {"productId":1, "text":"Product review", "rating":2}
   In case if you set product id which doesn't exist in the system or set rating not between 0 and 10, you'll receive error and review won't be added to the database. If POST request is success, you will receive added review in json format.
    
**Using Swagger**

For build interactive API documentation and testing application you can use Swagger framework

Run _application_, open browser and go to http://localhost:8080/swagger-ui.html and choose product-controller.


### Check database connection

Check successful connect to database from IntelliJ IDEA you can in right up tab called `Database`. 

Choose : `Data Source Properties` -> `Project Data Source` -> `Add` -> `MySql`.

Set :

    Name - [name_of_your_test_connection], 
    Host - localhost,
    Port - 3306, 
    User - [your_username], 
    Password - [your_password], 
    Database - [your_database_name]

 click - `Test Connection`.