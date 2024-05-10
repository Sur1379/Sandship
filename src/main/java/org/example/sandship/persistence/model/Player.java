package org.example.sandship.persistence.model;

import java.util.Map;

public class Player {

    private String nickname;

    private Map<Long, Warehouse> warehouseMap;

    public Player(String nickname, Map<Long, Warehouse> warehouseMap) {
        this.nickname = nickname;
        this.warehouseMap = warehouseMap;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Map<Long, Warehouse> getWarehouseMap() {
        return warehouseMap;
    }

    public void setWarehouseMap(Map<Long, Warehouse> warehouseMap) {
        this.warehouseMap = warehouseMap;
    }
}
