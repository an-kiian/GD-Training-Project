package store.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.controller.ProductController;
import store.mapper.EntityMapper;
import store.repository.ProductRepository;
import store.model.Product;
import store.dto.ProductDTO;
import store.service.ProductService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private EntityMapper<Product, ProductDTO> mapper = (EntityMapper<Product, ProductDTO>) EntityMapper.getInstance();

    @Autowired
    ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getProducts(String[] categories, boolean isReview) {
        LOGGER.info("ProductServiceImpl.getProducts:get product by categories" + categories + " with reviews:" + isReview);
        List<Product> products = categories == null ? productRepository.findAll() : productRepository.findByCategory(Arrays.asList(categories), categories.length);
        if (!isReview)
            products.forEach((u) -> u.setReviews(null));
        return products.stream().map(product -> mapper.toDTO(product, ProductDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ProductDTO updatePrice(ProductDTO productDTO) {
        LOGGER.info("ProductServiceImpl.updatePrice: Find product by id: " + productDTO.getId());
        Product productFromDB = productRepository.findById(productDTO.getId());
        if (productFromDB == null) {
            LOGGER.error("ProductServiceImpl.updatePrice: there aren't product with id:" + productDTO.getId());
            return null;
        }
        productFromDB.setPrice(productDTO.getPrice());
        return mapper.toDTO(productRepository.save(productFromDB), ProductDTO.class);
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        LOGGER.info("ProductServiceImpl.addProduct:map " + productDTO + " to Product");
        Product product = mapper.toEntity(productDTO, Product.class);
        return mapper.toDTO(productRepository.save(product), ProductDTO.class);
    }
}
