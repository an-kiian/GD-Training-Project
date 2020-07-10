package store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(long id) {
        super("Element with id=" + id + " not found");
    }

    public NotFoundException(String name) {
        super("Element with name=" + name + " not found");
    }

}
