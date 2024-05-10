package org.example.sandship.service;

import org.example.sandship.persistence.model.MaterialType;
import org.example.sandship.persistence.model.Player;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DataService {
    private static DataService instance;
    private Map<String, MaterialType> materialTypes;
    private Map<String, Player> players;

    private DataService() {
        materialTypes = new ConcurrentHashMap<>();
        players = new ConcurrentHashMap<>();
    }

    public static synchronized DataService getInstance() {
        if (Objects.isNull(instance)) {
            instance = new DataService();
        }

        return instance;
    }

    public Map<String, MaterialType> getMaterialTypes() {
        return materialTypes;
    }

    public Map<String, Player> getPlayers() {
        return players;
    }
}