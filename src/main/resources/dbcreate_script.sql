SET NAMES utf8;

CREATE DATABASE storedb;

USE storedb;

-- -------------------------------
-- PRODUCTS
-- -------------------------------
DROP TABLE IF EXISTS products;

CREATE TABLE products(
    id_prod INTEGER NOT NULL auto_increment PRIMARY KEY,
    name VARCHAR (100) NOT NULL,
    price DOUBLE NOT NULL,
    description VARCHAR (1000) NOT NULL
);



