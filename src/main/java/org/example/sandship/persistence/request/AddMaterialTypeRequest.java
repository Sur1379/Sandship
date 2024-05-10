package org.example.sandship.persistence.request;

import jakarta.validation.constraints.*;

public class AddMaterialTypeRequest {

    @NotBlank
    private String nickname;

    @NotNull
    @Positive
    private Long warehouseId;

    @Positive
    private Long fromWarehouseId;

    @NotBlank
    private String materialTypeName;

    @NotNull
    @Min(1)
    @Max(1000)
    private Integer count;

    public String getNickname() {
        return nickname;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public Integer getCount() {
        return count;
    }

    public Long getFromWarehouseId() {
        return fromWarehouseId;
    }
}
