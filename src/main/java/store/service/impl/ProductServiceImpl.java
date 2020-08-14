package store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.exception.ProductNotFoundException;
import store.mapper.EntityMapper;
import store.repository.ProductRepository;
import store.model.Product;
import store.dto.ProductDTO;
import store.service.ProductService;

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
    public List<ProductDTO> getProducts(List<String> categories, boolean showReview) {
        List<Product> products = showReview ? productRepository.findByCategoryWithReviews(categories, categories == null) : productRepository.findByCategory(categories, categories == null);
        return products.stream().map(product -> mapper.toDTO(product, ProductDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ProductDTO updatePrice(ProductDTO productDTO) {
        Product productFromDB = productRepository.findById(productDTO.getId());
        if (productFromDB == null)
            throw new ProductNotFoundException(productDTO.getId());
        productFromDB.setPrice(productDTO.getPrice());
        return mapper.toDTO(productRepository.save(productFromDB), ProductDTO.class);
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = mapper.toEntity(productDTO, Product.class);
        return mapper.toDTO(productRepository.save(product), ProductDTO.class);
    }

    @Override
    public Product checkProductAndUpdateRating(Long idProduct, double rating) {
        Product product = productRepository.findById(idProduct);
        if (product == null)
            throw new ProductNotFoundException(idProduct);
        double oldRating = product.getRating();
        rating = oldRating == 0 ? rating : ((oldRating + rating) / 2);
        product.setRating(rating);
        productRepository.save(product);
        return product;
    }
}
