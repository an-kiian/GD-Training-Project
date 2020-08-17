package store.exception;

public class SaleNotFoundException extends RuntimeException{

    public SaleNotFoundException(Long id) {
        super("Couldn't find product with id=" + id);
    }
}
