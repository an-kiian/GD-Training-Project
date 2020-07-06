package service;

import exception.NotFoundException;
import repository.impl.ProductRepositoryImpl;
import model.Product;
import request.ProductRequest;
import request.UpdateProductRequest;

import java.sql.SQLException;


public class ProductServiceImpl implements ProductService {
    ProductRepositoryImpl productRepository;
@Override
    public Product getProductById(long idProduct) {
    Product product= productRepository.findById(idProduct);
    if (product== null) {
        throw new NotFoundException(idProduct);
        return null;
    }
        return product;

    }
    @Override
    public Product getProductByName(String nameProduct) {
     Product product =null;
      try{
          product= productRepository.findByName(nameProduct);
        if (product== null) {
            //throw new NotFoundException(productId);
        }}
      catch(Exception e){
          return null;
      }
        return product;
    }
    public Product updatePrice(UpdateProductRequest updateRequest) {
        Product productFromDB=null;
    try{
        productFromDB = productRepository.findById(updateRequest.getIdProduct());
        if(productFromDB==null)
        {
            throw new NotFoundException(updateRequest.getIdProduct());
        }
           productFromDB.setPrice(updateRequest.getPrice());
           productRepository.save(productFromDB);}
       catch(SQLException e){
           return null;
       }
       catch (NotFoundException e){
            e.printStackTrace();
            return null;
       }
       return productFromDB;
    }
    public Product addProduct(ProductRequest productRequest) {
        Product product= new Product();
        product.setNameProduct(productRequest.getNameProduct());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        return productRepository.save(product);
    }
    public Product updatePrice2(long id, double price) {
        Product productFromDB=null;
        try{
            productFromDB = productRepository.findById(price);
            if(productFromDB==null)
            {
                throw new NotFoundException(id);
            }
            productFromDB.setPrice(price);
            productRepository.save(productFromDB);}
        catch(SQLException e){
            return null;
        }
        catch (NotFoundException e){
            e.printStackTrace();
            return null;
        }
        return productFromDB;
    }
}
