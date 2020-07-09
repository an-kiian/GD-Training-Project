package store.service;

import store.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.repository.ProductRepository;
import store.model.Product;
import store.request.ProductRequest;
import store.request.UpdateProductRequest;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {
    public ProductRepository productRepository;
    ;
 //   CategoryRepository categoryRepository;

    @Autowired
    ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(Long id) {
        Product product;
        try {
            product = productRepository.findByIdProduct(id);
            if (product == null)
                throw new NotFoundException(id);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return product;

    }
    @Override
    public Iterable<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductByName(String nameProduct) {
       List<Product> products = null;
        try {
            products = productRepository.findByName(nameProduct);
            if (products.isEmpty())
                throw new NotFoundException(nameProduct);
        } catch (NotFoundException e) {
            return null;
        }
        return products;
    }
    @Override
    public List<Product> getProductByDescription(String description) {
        List<Product> products = null;
        try {
            products = productRepository.findByDescription(description);
            if (products.isEmpty())
                throw new NotFoundException(description);
        } catch (NotFoundException e) {
            return null;
        }
        return products;
    }
    public Product updatePrice(UpdateProductRequest updateRequest) {
        Product productFromDB = null;
        try {
            productFromDB = productRepository.findByIdProduct(updateRequest.getIdProduct());
            if (productFromDB == null) {
                throw new NotFoundException(updateRequest.getIdProduct());
            }
            productFromDB.setPrice(updateRequest.getPrice());
            productRepository.save(productFromDB);
        } catch (NotFoundException e) {
            return null;
        }
        return productFromDB;
    }

    public Product addProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setNameProduct(productRequest.getNameProduct());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
       // long[] categories = productRequest.getCategories();
        // product.setCategories(categoriesFromArray(categories));
        return productRepository.save(product);
    }

    //    public Set<Category>categoriesFromArray(long []categories){
//        Set<Category> setCategories=new HashSet<>();
//        for(long category:categories)
//        {
//         try{
//             Category categ=categoryRepository.findByIdCategory(category);
//        if(categ!=null)
//            setCategories.add(categ);
//        else
//            throw new NotFoundException(category);
//
//        }catch(NotFoundException e){
//             e.printStackTrace();
//             return null;
//         }
//        }
//        return setCategories;
//    }
    public Product updatePrice2(Long id, double price) {
        Product productFromDB = null;
        try {
            productFromDB = productRepository.findByIdProduct(id);
            if (productFromDB == null) {
                throw new NotFoundException(id);
            }
            productFromDB.setPrice(price);
            productRepository.save(productFromDB);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return productFromDB;
    }
}
