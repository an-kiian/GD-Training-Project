package store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    private EntityMapper<Product, ProductDTO> mapper = (EntityMapper<Product, ProductDTO>) EntityMapper.getInstance();

    @Autowired
    ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getProducts(String[] category, boolean isReview) {
        List<Product> products;
        if (category != null)
            products = productRepository.findByCategory(Arrays.asList(category), ((category != null) ? category.length : 0));
        else
            products = productRepository.findAll();
        if (!isReview)
            products.forEach((u) -> u.setReviews(null));
        return products.stream().map(product -> mapper.toDTO(product, ProductDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ProductDTO updatePrice(ProductDTO productDTO) {
        Product productFromDB = productRepository.findById(productDTO.getId());
        if (productFromDB == null)
            return null;
        productFromDB.setPrice(productDTO.getPrice());
        return mapper.toDTO(productRepository.save(productFromDB), ProductDTO.class);
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = mapper.toEntity(productDTO, Product.class);
        return mapper.toDTO(productRepository.save(product), ProductDTO.class);
    }
}
