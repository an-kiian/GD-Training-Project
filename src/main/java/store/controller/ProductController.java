package store.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import store.dto.ProductDTO;
import store.service.ProductService;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@Validated
public class ProductController {
    private ProductService productService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("store/product")
    public List<ProductDTO> getProducts(@QueryParam("categories") String[] categories, @QueryParam("showReview") boolean showReview) {
    LOGGER.info("ProductController: GET request  for getting products by categories with url 'store/product'");
        return productService.getProducts(categories, showReview);
    }

    @PutMapping("/store/product/price")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO updatePrice(@RequestBody @Valid ProductDTO productDTO) {
        LOGGER.info("ProductController: PUT request for updating product with url 'store/product/price'");
        return productService.updatePrice(productDTO);
    }

    @PostMapping("/store/product")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO addProduct(@RequestBody @Valid ProductDTO productDTO) {
        LOGGER.info("ProductController: POST request for adding product with url 'store/product'");
        return productService.addProduct(productDTO);
    }
}
