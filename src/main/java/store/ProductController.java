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

    @GetMapping("store/product/getById/{idProduct}")
    public ProductDTO getProductById(@PathVariable Long idProduct) {
        return productService.getProductById(idProduct);
    }

    @GetMapping("store/product/getAll")
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/store/product/getByName/{nameProduct}")
    public List<ProductDTO> getProductByName(@PathVariable String nameProduct) {
        return productService.getProductByName(nameProduct);
    }

    @PutMapping("/store/product/updatePrice")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO updatePrice(@RequestBody ProductDTO productDTO) {
        return productService.updatePrice(productDTO);
    }

    @PostMapping("/store/product/addProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }
}
