package Controllers;

import Services.ProductService;
import Services.ProductServiceImpl;
import model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ProductController {
    ProductServiceImpl productService;
    @GetMapping("/store/product/getById")
    public Product getProductById(@RequestBody long idProduct) {
        return productService.getProductById(idProduct);
    }

    @GetMapping("/store/product/getByName")
    public List<Product> getProductByField(@RequestBody String nameProduct) {
        return productService.getProductByName(nameProduct);
    }

    @PutMapping("/store/product/updatePrice")
    public Product updatePrice(@RequestBody long idProduct, double price) {
        return productService.updatePrice(idProduct, price);
    }

    //TODO: how to write category
    @PostMapping("/store/product/addProduct")
    public void addProduct(@RequestBody Product product) {
    }
}
