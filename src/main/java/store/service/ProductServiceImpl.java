package store.service;

import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.repository.ProductRepository;
import store.model.Product;
import store.dto.ProductDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    public ProductRepository productRepository;
    private EntityMapper mapper;

    @Autowired
    ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.mapper = new EntityMapper();
    }

    @Override
    public ProductDTO getProductById(Long id) {
        return mapper.toDTO(productRepository.findByIdProduct(id));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> productsDTO = new ArrayList<>();
        productRepository.findAll().forEach(product -> productsDTO.add(mapper.toDTO(product)));
        return productsDTO;
    }

    @Override
    public List<ProductDTO> getProductByName(String nameProduct) {
        List<ProductDTO> productsDTO = new ArrayList<>();
        productRepository.findByName(nameProduct).forEach(product -> productsDTO.add(mapper.toDTO(product)));
        return productsDTO;
    }

    @Override
    public ProductDTO updatePrice(ProductDTO productDTO) {
        Product productFromDB = productRepository.findByIdProduct(productDTO.getIdProduct());
        if (productFromDB == null)
            return null;
        productFromDB.setPrice(productDTO.getPrice());
        return mapper.toDTO(productRepository.save(productFromDB));
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = mapper.toEntity(productDTO);
        return mapper.toDTO(productRepository.save(product));
    }
}
