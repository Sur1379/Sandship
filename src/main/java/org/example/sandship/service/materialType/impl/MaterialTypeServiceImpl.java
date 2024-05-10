package org.example.sandship.service.materialType.impl;

import org.example.sandship.exception.MaxLimitException;
import org.example.sandship.exception.ModelExistsException;
import org.example.sandship.exception.NotFoundException;
import org.example.sandship.helper.Message;
import org.example.sandship.persistence.model.Capacity;
import org.example.sandship.persistence.model.MaterialType;
import org.example.sandship.persistence.model.Player;
import org.example.sandship.persistence.model.Warehouse;
import org.example.sandship.persistence.request.AddMaterialTypeRequest;
import org.example.sandship.persistence.request.CreateMaterialTypeRequest;
import org.example.sandship.persistence.request.GetMaterialTypeRequest;
import org.example.sandship.persistence.request.UpgradeCapacityRequest;
import org.example.sandship.service.DataService;
import org.example.sandship.service.materialType.MaterialTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Objects;

public class MaterialTypeServiceImpl implements MaterialTypeService {

    private final DataService service;

    @Autowired
    public MaterialTypeServiceImpl(DataService service) {
        this.service = service;
    }

    @Override
    public MaterialType createMaterialType(CreateMaterialTypeRequest request) {
        Map<String, MaterialType> materialTypes = service.getMaterialTypes();
        String slug = request.getName().toLowerCase().replaceAll("\\s", "_");

        if (!materialTypes.containsKey(slug)) {
            MaterialType materialType = new MaterialType(request.getName(), slug,
                    request.getDescription(), request.getIcon());
            materialTypes.put(slug, materialType);

            return materialType;
        } else {
            throw new ModelExistsException(Message.ERROR_MATERIAL_TYPE_EXISTS.getMessage());
        }
    }

    @Override
    public MaterialType getMaterialType(GetMaterialTypeRequest request) {
        Map<String, MaterialType> materialTypes = service.getMaterialTypes();
        String slug = request.getName().toLowerCase().replaceAll("\\s", "_");

        if (materialTypes.containsKey(slug)) {
            return materialTypes.get(slug);
        } else {
            throw new NotFoundException(Message.ERROR_MATERIAL_TYPE_NOT_FOUND.getMessage());
        }
    }

    @Override
    public boolean deleteMaterialType(GetMaterialTypeRequest request) {
        Map<String, MaterialType> materialTypes = service.getMaterialTypes();
        String slug = request.getName().toLowerCase().replaceAll("\\s", "_");

        if (materialTypes.containsKey(slug)) {
            materialTypes.remove(slug);

            return true;
        } else {
            throw new NotFoundException(Message.ERROR_MATERIAL_TYPE_NOT_FOUND.getMessage());
        }
    }

    @Override
    public Capacity addMaterialToWarehouse(AddMaterialTypeRequest request) {
        String slug = request.getMaterialTypeName().toLowerCase().replaceAll("\\s", "_");
        String nickname = request.getNickname().toLowerCase();

        if (service.getMaterialTypes().containsKey(slug)) {
            MaterialType materialType = service.getMaterialTypes().get(slug);

            if (service.getPlayers().containsKey(nickname)) {
                Player player = service.getPlayers().get(nickname);
                Map<Long, Warehouse> warehouseMap = player.getWarehouseMap();

                if (warehouseMap.containsKey(request.getWarehouseId())) {
                    Warehouse warehouse = warehouseMap.get(request.getWarehouseId());
                    Map<MaterialType, Capacity> materials = warehouse.getMaterials();
                    Long fromWarehouseId = request.getFromWarehouseId();

                    Capacity fromCapacity = null;
                    Map<MaterialType, Capacity> fromWarehouseMaterials = null;

                    if (Objects.nonNull(fromWarehouseId)) {
                        if (warehouseMap.containsKey(fromWarehouseId)) {
                            Warehouse fromWarehouse = warehouseMap.get(fromWarehouseId);
                            fromWarehouseMaterials = fromWarehouse.getMaterials();

                            if (fromWarehouseMaterials.containsKey(materialType)) {
                                fromCapacity = fromWarehouseMaterials.get(materialType);

                                if (request.getCount() > fromCapacity.getCurrent()) {
                                    throw new MaxLimitException(Message.ERROR_MATERIAL_TYPE_MAX_LIMIT_MOVE.getMessage());
                                }
                            } else {
                                throw new NotFoundException(Message.ERROR_MATERIAL_TYPE_NOT_FOUND.getMessage());
                            }
                        } else {
                            throw new NotFoundException(Message.ERROR_WAREHOUSE_NOT_FOUND.getMessage());
                        }

                    }

                    if (!materials.containsKey(materialType)) {
                        if (Objects.nonNull(fromWarehouseId)) {
                            if (request.getCount() > 1000) {
                                throw new MaxLimitException(Message.ERROR_MATERIAL_TYPE_MAX_LIMIT.getMessage());
                            }

                            fromCapacity.setCurrent(fromCapacity.getCurrent() - request.getCount());

                            if (fromCapacity.getCurrent() == 0) {
                                fromWarehouseMaterials.remove(materialType);
                            }
                        }

                        Capacity capacity = new Capacity(request.getCount());
                        materials.put(materialType, capacity);

                        return capacity;
                    } else {
                        Capacity capacity = materials.get(materialType);

                        if (request.getCount() <= capacity.getAllowedRange()) {
                            if (Objects.nonNull(fromWarehouseId)) {
                                fromCapacity.setCurrent(fromCapacity.getCurrent() - request.getCount());

                                if (fromCapacity.getCurrent() == 0) {
                                    fromWarehouseMaterials.remove(materialType);
                                }
                            }

                            capacity.setCurrent(capacity.getCurrent() + request.getCount());

                            return capacity;
                        } else {
                            throw new MaxLimitException(Message.ERROR_MATERIAL_TYPE_MAX_LIMIT.getMessage());
                        }
                    }
                } else {
                    throw new NotFoundException(Message.ERROR_WAREHOUSE_NOT_FOUND.getMessage());
                }
            } else {
                throw new NotFoundException(Message.ERROR_PLAYER_NOT_FOUND.getMessage());
            }
        } else {
            throw new NotFoundException(Message.ERROR_MATERIAL_TYPE_NOT_FOUND.getMessage());
        }
    }

    @Override
    public Capacity upgradeCapacity(UpgradeCapacityRequest request) {
        String slug = request.getMaterialTypeName().toLowerCase().replaceAll("\\s", "_");
        String nickname = request.getNickname().toLowerCase();

        if (service.getMaterialTypes().containsKey(slug)) {
            MaterialType materialType = service.getMaterialTypes().get(slug);

            if (service.getPlayers().containsKey(nickname)) {
                Player player = service.getPlayers().get(nickname);
                Map<Long, Warehouse> warehouseMap = player.getWarehouseMap();

                if (warehouseMap.containsKey(request.getWarehouseId())) {
                    Warehouse warehouse = warehouseMap.get(request.getWarehouseId());
                    Map<MaterialType, Capacity> materials = warehouse.getMaterials();

                    if (materials.containsKey(materialType)) {
                        Capacity capacity = materials.get(materialType);

                        capacity.setMax(capacity.getMax() + request.getCapacity());

                        return capacity;
                    } else {
                        throw new NotFoundException(Message.ERROR_MATERIAL_TYPE_NOT_FOUND.getMessage());
                    }
                } else {
                    throw new NotFoundException(Message.ERROR_WAREHOUSE_NOT_FOUND.getMessage());
                }
            } else {
                throw new NotFoundException(Message.ERROR_PLAYER_NOT_FOUND.getMessage());
            }
        } else {
            throw new NotFoundException(Message.ERROR_MATERIAL_TYPE_NOT_FOUND.getMessage());
        }
    }
}
