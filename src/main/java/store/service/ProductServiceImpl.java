package store.service;

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
    static ModelMapper modelMapper = new ModelMapper();

    @Autowired
    ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO getProductById(Long id) {

        return mapToDTO(productRepository.findByIdProduct(id));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> productsDTO = new ArrayList<>();
        productRepository.findAll().forEach(product -> productsDTO.add(mapToDTO(product)));
        return productsDTO;
    }

    @Override
    public List<ProductDTO> getProductByName(String nameProduct) {
        List<ProductDTO> productsDTO = new ArrayList<>();
        productRepository.findByName(nameProduct).forEach(product -> productsDTO.add(mapToDTO(product)));
        return productsDTO;
    }

    @Override
    public ProductDTO updatePrice(ProductDTO productDTO) {
        Product productFromDB = productRepository.findByIdProduct(productDTO.getIdProduct());
        if (productFromDB == null)
            return null;
        productFromDB.setPrice(productDTO.getPrice());
        return mapToDTO(productRepository.save(productFromDB));
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = mapToEntity(productDTO);
        return mapToDTO(productRepository.save(product));
    }

    private ProductDTO mapToDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    private Product mapToEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }
}
