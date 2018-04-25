package entrance.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApplicationNotFoundException extends RuntimeException {

    private static final String NOT_FOUND_BY_CONTACT_ID = "Contact with id [%d] has no applications.";

    public ApplicationNotFoundException(Long contactId) {
        super(String.format(NOT_FOUND_BY_CONTACT_ID, contactId));
    }
}
