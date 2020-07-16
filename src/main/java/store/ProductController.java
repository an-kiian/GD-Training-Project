package store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import store.dto.ProductDTO;
import store.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;

    @Autowired
    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("store/product/{id}")
    public ProductDTO getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("store/product")
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/store/product/name/{name}")
    public List<ProductDTO> getProductByName(@PathVariable String name) {
        return productService.getProductByName(name);
    }

    @PutMapping("/store/product/price")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO updatePrice(@RequestBody ProductDTO productDTO) {
        return productService.updatePrice(productDTO);
    }

    @PostMapping("/store/product")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }
}
