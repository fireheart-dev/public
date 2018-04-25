package entrance.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContactNotFoundException extends RuntimeException {

    private static final String BASE_MESSAGE = "Contact with id = [%d] is not found.";

    public ContactNotFoundException(Long contactId) {
        super(String.format(BASE_MESSAGE, contactId));
    }
}
