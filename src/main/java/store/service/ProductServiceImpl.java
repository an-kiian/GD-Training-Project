package store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import store.mapper.EntityMapper;
import store.repository.ProductRepository;
import store.model.Product;
import store.dto.ProductDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private EntityMapper<Product, ProductDTO> mapper = (EntityMapper<Product, ProductDTO>) EntityMapper.getInstance();
    @Value("${categories.list}")
    private String[] allCategories;

    @Autowired
    ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getProducts(List<String> category) {

        List<Product> products = productRepository.findByCategory(category, category.size());
        List<ProductDTO> productsDTO = products.stream().map(product -> mapper.toDTO(product, ProductDTO.class)).collect(Collectors.toList());
        return productsDTO;
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
        List<String> allCategories = Arrays.asList(this.allCategories);
        boolean result = allCategories.containsAll(productDTO.getCategories());
        if (!result)
            return null;
        Product product = mapper.toEntity(productDTO, Product.class);
        return mapper.toDTO(productRepository.save(product), ProductDTO.class);
    }
}
