package org.example.sandship.persistence.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class WarehouseRequest {
    @NotBlank
    private String nickname;

    @Positive
    private Long warehouseId;

    public String getNickname() {
        return nickname;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }
}
