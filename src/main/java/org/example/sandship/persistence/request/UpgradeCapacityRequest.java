package org.example.sandship.persistence.request;

import jakarta.validation.constraints.*;

public class UpgradeCapacityRequest {

    @NotBlank
    private String nickname;

    @NotNull
    @Positive
    private Long warehouseId;

    @NotBlank
    private String materialTypeName;

    @NotNull
    @Min(1000)
    private Integer capacity;

    public String getNickname() {
        return nickname;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public Integer getCapacity() {
        return capacity;
    }
}
