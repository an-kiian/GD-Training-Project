package Services;

import model.Product;

import java.util.List;

public interface ProductService {
    public Product getProductById(long productId);
    public List<Product> getProductByName(String nameProduct);
    public Product updatePrice(long idProduct, double price) ;
}
