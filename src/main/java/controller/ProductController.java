package controller;

import org.springframework.http.HttpStatus;
import request.ProductRequest;
import request.UpdateProductRequest;
import service.ProductServiceImpl;
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
    public Product getProductByField(@RequestBody String nameProduct) {
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
    @PutMapping("/store/product/updatePrice2")
    public Product updatePrice2(@PathVariable("idProduct") long id, @RequestBody double price) {
        return productService.updatePrice2(id, price);
    }
}
