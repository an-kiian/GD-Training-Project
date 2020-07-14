package store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.repository.ProductRepository;
import store.model.Product;
import store.request.ProductRequest;
import store.request.UpdateProductRequest;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    public ProductRepository productRepository;

    @Autowired
    ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findByIdProduct(id);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public List<Product> getProductByName(String nameProduct) {
        return productRepository.findByName(nameProduct);
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
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        return productRepository.save(product);
    }
}
