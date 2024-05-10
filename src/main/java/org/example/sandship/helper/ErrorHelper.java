package org.example.sandship.helper;

import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ErrorHelper {
    public static Map<Object, Object> getErrors(BindingResult result) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("errors", result.
                getFieldErrors().
                stream().
                collect(Collectors.toMap(error -> error.getField(), error -> error.getDefaultMessage())));

        return map;
    }
}
