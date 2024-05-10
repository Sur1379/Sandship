package org.example.sandship.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class ModelExistsException extends RuntimeException {

    public ModelExistsException(String message) {
        super(message);
    }
}
