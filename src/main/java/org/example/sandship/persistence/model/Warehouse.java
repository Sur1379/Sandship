package org.example.sandship.persistence.model;

import java.util.Map;

public class Warehouse {
    private static Long nextId = 1L;

    private Long id;

    private Map<MaterialType, Capacity> materials;

    public Warehouse(Map<MaterialType, Capacity> materials) {
        this.id = nextId;
        this.materials = materials;
        nextId++;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<MaterialType, Capacity> getMaterials() {
        return materials;
    }

    public void setMaterials(Map<MaterialType, Capacity> materials) {
        this.materials = materials;
    }
}
