package org.example.sandship.persistence.request;

import jakarta.validation.constraints.NotBlank;

public class CreateMaterialTypeRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String icon;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
