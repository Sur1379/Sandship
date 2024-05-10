package org.example.sandship.service.materialType;

import org.example.sandship.persistence.model.Capacity;
import org.example.sandship.persistence.model.MaterialType;
import org.example.sandship.persistence.request.AddMaterialTypeRequest;
import org.example.sandship.persistence.request.CreateMaterialTypeRequest;
import org.example.sandship.persistence.request.GetMaterialTypeRequest;
import org.example.sandship.persistence.request.UpgradeCapacityRequest;

public interface MaterialTypeService {

    MaterialType createMaterialType(CreateMaterialTypeRequest request);

    MaterialType getMaterialType(GetMaterialTypeRequest request);

    boolean deleteMaterialType(GetMaterialTypeRequest request);

    Capacity addMaterialToWarehouse(AddMaterialTypeRequest request);

    Capacity upgradeCapacity(UpgradeCapacityRequest request);
}
