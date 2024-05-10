package org.example.sandship.persistence.request;

import jakarta.validation.constraints.NotBlank;

public class GetMaterialTypeRequest {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }
}
