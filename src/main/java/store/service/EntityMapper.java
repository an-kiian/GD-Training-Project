package store.service;

import org.modelmapper.ModelMapper;
import store.dto.ProductDTO;
import store.model.Product;

public class EntityMapper {
    private ModelMapper modelMapper;

    EntityMapper() {
        this.modelMapper = new ModelMapper();
    }

    ProductDTO toDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    Product toEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }
}
