package org.example.sandship.controller;

import jakarta.validation.Valid;
import org.example.sandship.helper.ErrorHelper;
import org.example.sandship.helper.Message;
import org.example.sandship.persistence.model.Warehouse;
import org.example.sandship.persistence.request.WarehouseRequest;
import org.example.sandship.service.warehouse.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    @Autowired
    WarehouseService service;

    @PostMapping("/create")
    public ResponseEntity<?> createWarehouse(@Valid @RequestBody WarehouseRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.unprocessableEntity()
                    .body(ErrorHelper.getErrors(result));
        }

        Warehouse warehouse = service.createWarehouse(request.getNickname());

        return ResponseEntity.ok(warehouse);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getWarehouse(@Valid @RequestBody WarehouseRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.unprocessableEntity()
                    .body(ErrorHelper.getErrors(result));
        }

        if (Objects.isNull(request.getWarehouseId())) {
            HashMap<String, String> map = new HashMap<>();
            map.put("warehouseId", Message.ERROR_WAREHOUSE_ID_REQUIRED.getMessage());

            return ResponseEntity.unprocessableEntity()
                    .body(map);
        }

        Warehouse warehouse = service.getWarehouse(request.getNickname(), request.getWarehouseId());

        return ResponseEntity.ok(warehouse);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteWarehouse(@Valid @RequestBody WarehouseRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.unprocessableEntity()
                    .body(ErrorHelper.getErrors(result));
        }

        if (Objects.isNull(request.getWarehouseId())) {
            HashMap<String, String> map = new HashMap<>();
            map.put("warehouseId", Message.ERROR_WAREHOUSE_ID_REQUIRED.getMessage());

            return ResponseEntity.unprocessableEntity()
                    .body(map);
        }

        boolean deleted = service.deleteWarehouse(request.getNickname(), request.getWarehouseId());

        return ResponseEntity.ok(deleted);
    }
}
