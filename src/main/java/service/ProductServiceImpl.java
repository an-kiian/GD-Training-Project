package service;

import exception.NotFoundException;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CategoryRepository;
import repository.ProductRepository;
import model.Product;
import request.ProductRequest;
import request.UpdateProductRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;;
    CategoryRepository categoryRepository;
    ProductService productService;
    @Autowired
    ProductServiceImpl(ProductRepository productRepository){
        this.productRepository=productRepository;
    }
@Override
    public Product getProductById(long idProduct) {
    Product product;
    try{
        product= productRepository.findById(idProduct);
    if (product== null)
        throw new NotFoundException(idProduct);
    }
    catch(NotFoundException e){
    e.printStackTrace();
    return null;
    }
        return product;

    }
    @Override
    public List<Product> getProductByName(String nameProduct) {
    List<Product> products =null;
      try{
          products=productRepository.findByName(nameProduct);
        if (products== null) {
            throw new NotFoundException(nameProduct);
        }}
      catch(NotFoundException e){
          e.printStackTrace();
          return null;
      }
        return products;
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
        long [] categories=productRequest.getCategories();
        product.setCategories(categoriesFromArray(categories));
        return productRepository.save(product);
    }
    public Set<Category>categoriesFromArray(long []categories){
        Set<Category> setCategories=new HashSet<>();
        for(long category:categories)
        {
         try{
             Category categ=categoryRepository.findById(category);
        if(categ!=null)
            setCategories.add(categ);
        else
            throw new NotFoundException(category);

        }catch(NotFoundException e){
             e.printStackTrace();
             return null;
         }
        }
        return setCategories;
    }
    public Product updatePrice2(long id, double price) {
        Product productFromDB=null;
        try{
            productFromDB = productRepository.findById(id);
            if(productFromDB==null)
            {
                throw new NotFoundException(id);
            }
            productFromDB.setPrice(price);
            productRepository.save(productFromDB);}
        catch (NotFoundException e){
            e.printStackTrace();
            return null;
        }
        return productFromDB;
    }
    public List<Product> getAllProduct(){
    return productRepository.findAll();
    }
}
