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
    //   CategoryRepository categoryRepository;

    @Autowired
    ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findByIdProduct(id);
    }

    @Override
    public Iterable<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductByName(String nameProduct) {
        return productRepository.findByName(nameProduct);
    }

    @Override
    public List<Product> getProductByDescription(String description) {
        return productRepository.findByDescription(description);
    }

    public Product updatePrice(UpdateProductRequest updateRequest) {
        Product productFromDB = productRepository.findByIdProduct(updateRequest.getIdProduct());
        if (productFromDB == null)
            return null;
        productFromDB.setPrice(updateRequest.getPrice());
        productRepository.save(productFromDB);

        return productFromDB;
    }

    public Product addProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getNameProduct());
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
