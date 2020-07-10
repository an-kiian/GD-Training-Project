package store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import store.request.ProductRequest;
import store.request.UpdateProductRequest;
import store.service.ProductService;
import store.model.Product;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    public ProductService productService;

    @Autowired
    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("store/product/getById/{idProduct}")
    public Product getProductById(@PathVariable Long idProduct) {
        return productService.getProductById(idProduct);
    }

    @GetMapping("store/product/getAll")
    public Iterable<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping("/store/product/getByName/{nameProduct}")
    public Iterable<Product> getProductByName(@PathVariable String nameProduct) {
        return productService.getProductByName(nameProduct);
    }

    @PutMapping("/store/product/updatePrice")
    public Product updatePrice(@RequestBody UpdateProductRequest updateRequest) {
        return productService.updatePrice(updateRequest);
    }

    @PostMapping("/store/product/addProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody ProductRequest productRequest) {
        return productService.addProduct(productRequest);
    }
}
