package org.example.sandship.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class MaxLimitException extends RuntimeException {

    public MaxLimitException(String message) {
        super(message);
    }
}
