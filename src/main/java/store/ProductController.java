package store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import store.dto.ProductDTO;
import store.service.ProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
public class ProductController {
    private ProductService productService;

    @Autowired
    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("store/product")
    public List<ProductDTO> getProducts(@RequestParam("categories") List<String> categories) {
        return productService.getProducts(categories);
    }

    @PutMapping("/store/product/price")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO updatePrice(@RequestBody @Valid ProductDTO productDTO) {
        return productService.updatePrice(productDTO);
    }

    @PostMapping("/store/product")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO addProduct(@RequestBody @Valid ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }
}
