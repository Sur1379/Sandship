package org.example.sandship.service.warehouse.impl;

import org.example.sandship.exception.MaxLimitException;
import org.example.sandship.exception.NotFoundException;
import org.example.sandship.helper.Message;
import org.example.sandship.persistence.model.Player;
import org.example.sandship.persistence.model.Warehouse;
import org.example.sandship.service.DataService;
import org.example.sandship.service.warehouse.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final DataService service;

    @Autowired
    public WarehouseServiceImpl(DataService service) {
        this.service = service;
    }

    @Override
    public Warehouse createWarehouse(String nickname) {
        nickname = nickname.toLowerCase();

        if (service.getPlayers().containsKey(nickname)) {
            Player player = service.getPlayers().get(nickname);

            if (player.getWarehouseMap() != null) {
                if (player.getWarehouseMap().size() < 10) {
                    Warehouse warehouse = new Warehouse(new HashMap<>());
                    player.getWarehouseMap().put(warehouse.getId(), warehouse);

                    return warehouse;
                } else {
                    throw new MaxLimitException(Message.ERROR_PLAYER_MAX_WAREHOUSES.getMessage());
                }
            } else {
                throw new NotFoundException(Message.ERROR_WAREHOUSE_NOT_FOUND.getMessage());
            }
        } else {
            throw new NotFoundException(Message.ERROR_PLAYER_NOT_FOUND.getMessage());
        }
    }

    @Override
    public Warehouse getWarehouse(String nickname, Long warehouseId) {
        Map<String, Player> players = service.getPlayers();
        nickname = nickname.toLowerCase();

        if (players != null && players.containsKey(nickname)) {
            Player player = players.get(nickname);

            Map<Long, Warehouse> warehouseMap = player.getWarehouseMap();

            if (warehouseMap != null && warehouseMap.containsKey(warehouseId)) {
                return warehouseMap.get(warehouseId);
            } else {
                throw new NotFoundException(Message.ERROR_WAREHOUSE_NOT_FOUND.getMessage());
            }
        } else {
            throw new NotFoundException(Message.ERROR_PLAYER_NOT_FOUND.getMessage());
        }
    }

    @Override
    public boolean deleteWarehouse(String nickname, Long warehouseId) {
        Map<String, Player> players = service.getPlayers();
        nickname = nickname.toLowerCase();

        if (players != null && players.containsKey(nickname)) {
            Player player = players.get(nickname);

            Map<Long, Warehouse> warehouseMap = player.getWarehouseMap();

            if (warehouseMap != null && warehouseMap.containsKey(warehouseId)) {
                warehouseMap.remove(warehouseId);

                return true;
            } else {
                throw new NotFoundException(Message.ERROR_WAREHOUSE_NOT_FOUND.getMessage());
            }
        } else {
            throw new NotFoundException(Message.ERROR_PLAYER_NOT_FOUND.getMessage());
        }
    }
}
