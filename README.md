# Overview

GD-Training-Project is an implementation of the online store back-end functionality.
Checking the correctness of the developed functionality can be done via Postman.
The main functionality that is implemented in the project is working with a database, namely

Product management:

- adding a product

- product price update

- receiving all products stored in the database

- receiving products by their name or unique id.

## Summary

  - [Getting Started](#getting-started)
  - [Installing and Deployment](#deployment)
  - [Built With](#built-with)


## Getting Started

It is needed to clone project to the local machine. For this you need to perform:

    git clone https://github.com/an-kiian/GD-Training-Project

## Prerequisites

**Maven**

For install maven you need to download package from the official [resource](https://maven.apache.org/download.cgi) and [install](https://mkyong.com/maven/how-to-install-maven-in-windows/) it.

**IntelliJ IDEA**

You need to install Ultimate [version](https://www.jetbrains.com/idea/promo/ultimate/?gclid=CjwKCAjwmMX4BRAAEiwA-zM4JogGjxxekfBMsDbtXyjyMEA_iB-_ii3aldVTHvjOrl7fOgTLPl3d8RoCVvUQAvD_BwE) for project use. 
Additional you need to add Lombok plugin according to the [guideline](https://projectlombok.org/setup/intellij).

**Postman**

Postman can be downloaded from the official [website](https://www.postman.com/downloads/)


## Installing and Deployment
### Start service
Further launching of the project is possible in two ways: using the command line or using the IntelliJ IDEA.

**Using command line**

To start the project from the command line, you need to run the following  commands sequence  from the .../GD-Training-Project/
    
    mvn clean
    mvn compile
    mvn spring-boot:run
    
**Using IntelliJ IDEA**

For correct project start with IntelliJ IDEA you need to 
run class /GD-Training-Project/src/main/java/store/Application.java

### Check service functionality

It is possible to use postman [collection](https://www.getpostman.com/collections/6e08a7f7503e5e63101c) for check service or make own requests.

There are 3 types of requests: *GET*, *POST* and *PUT* in the system.

1.GET:
- get product by id: http://localhost:8080/store/product/{id}, where {id} is a needed product id.
- get product by name: http://localhost:8080/store/product/{name}, where {name} is a needed product name.

If GET request is success, you will receive product in json format.
2.POST:
add new product: http://localhost:8080/store/product/) with body in json format as:
    
    
    {"name":"Product name", "price":100, "description":"Product description}
   If POST request is success, you will receive added product in json format.
    
3.PUT:
update product price: http://localhost:8080/store/product/price with body in json format as:

    {"id":1, "price":200}
If PUT request is success and there is a product with specified id in the database, you will receive updated product in json format.    




