package org.example.sandship.controller;

import jakarta.validation.Valid;
import org.example.sandship.helper.ErrorHelper;
import org.example.sandship.helper.Message;
import org.example.sandship.persistence.model.Capacity;
import org.example.sandship.persistence.model.MaterialType;
import org.example.sandship.persistence.request.AddMaterialTypeRequest;
import org.example.sandship.persistence.request.CreateMaterialTypeRequest;
import org.example.sandship.persistence.request.GetMaterialTypeRequest;
import org.example.sandship.persistence.request.UpgradeCapacityRequest;
import org.example.sandship.service.materialType.MaterialTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/material-types")
public class MaterialTypeController {

    @Autowired
    MaterialTypeService service;

    @PostMapping("/create")
    public ResponseEntity<?> createMaterialType(@Valid @RequestBody CreateMaterialTypeRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.unprocessableEntity()
                    .body(ErrorHelper.getErrors(result));
        }

        MaterialType materialType = service.createMaterialType(request);

        return ResponseEntity.ok(materialType);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getMaterialType(@Valid @RequestBody GetMaterialTypeRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.unprocessableEntity()
                    .body(ErrorHelper.getErrors(result));
        }

        MaterialType materialType = service.getMaterialType(request);

        return ResponseEntity.ok(materialType);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteMaterialType(@Valid @RequestBody GetMaterialTypeRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.unprocessableEntity()
                    .body(ErrorHelper.getErrors(result));
        }

        boolean deleted = service.deleteMaterialType(request);

        return ResponseEntity.ok(deleted);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMaterialType(@Valid @RequestBody AddMaterialTypeRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.unprocessableEntity()
                    .body(ErrorHelper.getErrors(result));
        }

        Capacity capacity = service.addMaterialToWarehouse(request);

        return ResponseEntity.ok(capacity);
    }

    @PostMapping("/move")
    public ResponseEntity<?> moveMaterialType(@Valid @RequestBody AddMaterialTypeRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.unprocessableEntity()
                    .body(ErrorHelper.getErrors(result));
        }

        if (Objects.isNull(request.getFromWarehouseId())) {
            HashMap<String, String> map = new HashMap<>();
            map.put("warehouseId", Message.ERROR_WAREHOUSE_ID_REQUIRED.getMessage());

            return ResponseEntity.unprocessableEntity()
                    .body(map);
        }

        Capacity capacity = service.addMaterialToWarehouse(request);

        return ResponseEntity.ok(capacity);
    }

    @PostMapping("/upgrade-capacity")
    public ResponseEntity<?> upgradeMaterialTypeCapacity(@Valid @RequestBody UpgradeCapacityRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.unprocessableEntity()
                    .body(ErrorHelper.getErrors(result));
        }

        Capacity capacity = service.upgradeCapacity(request);

        return ResponseEntity.ok(capacity);
    }
}
