package store.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long idProduct) {
        super("Couldn't find product with id=" + idProduct);
    }
}
