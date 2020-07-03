package Services;

import Repositories.ProductRepositoryImpl;
import Repositories.interfaces.ProductRepository;
import model.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    ProductRepositoryImpl productRepository;
@Override
    public Product getProductById(long productId) {
        return productRepository.getProfileById(productId);

    }
    @Override
    public List<Product> getProductByName(String nameProduct) {
        return productRepository.getProfileById(nameProduct);

    }
    public Product updatePrice(long idProduct, double price) {
        return productRepository.updatePrice(idProduct,price);

    }
}
