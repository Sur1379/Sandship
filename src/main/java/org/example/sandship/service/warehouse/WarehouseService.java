package org.example.sandship.service.warehouse;

import org.example.sandship.persistence.model.Warehouse;

public interface WarehouseService {

    Warehouse createWarehouse(String nickname);

    Warehouse getWarehouse(String nickname, Long warehouseId);

    boolean deleteWarehouse(String nickname, Long warehouseId);
}
