package store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.mapper.EntityMapper;
import store.repository.ProductRepository;
import store.model.Product;
import store.dto.ProductDTO;
import store.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private EntityMapper<Product, ProductDTO> mapper = new EntityMapper<>();

    @Autowired
    ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return mapper.toDTO(productRepository.findById(id), ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> productsDTO = new ArrayList<>();
        productRepository.findAll().forEach(product -> productsDTO.add(mapper.toDTO(product, ProductDTO.class)));
        return productsDTO;
    }

    @Override
    public List<ProductDTO> getProductByName(String nameProduct) {
        List<ProductDTO> productsDTO = new ArrayList<>();
        productRepository.findByName(nameProduct).forEach(product -> productsDTO.add(mapper.toDTO(product, ProductDTO.class)));
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
        Product product = mapper.toEntity(productDTO, Product.class);
        return mapper.toDTO(productRepository.save(product), ProductDTO.class);
    }
}
