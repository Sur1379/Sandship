package org.example.sandship.service.player.impl;

import org.example.sandship.exception.ModelExistsException;
import org.example.sandship.exception.NotFoundException;
import org.example.sandship.helper.Message;
import org.example.sandship.persistence.model.Player;
import org.example.sandship.service.DataService;
import org.example.sandship.service.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final DataService service;

    @Autowired
    public PlayerServiceImpl(DataService service) {
        this.service = service;
    }

    @Override
    public Player createPlayer(String nickname) {
        nickname = nickname.toLowerCase();

        if (!service.getPlayers().containsKey(nickname)) {
            Player player = new Player(nickname, new HashMap<>());
            service.getPlayers().put(nickname, player);

            return player;
        } else {
            throw new ModelExistsException(Message.ERROR_PLAYER_EXISTS.getMessage());
        }
    }

    @Override
    public Player getPlayer(String nickname) {
        nickname = nickname.toLowerCase();

        if (service.getPlayers().containsKey(nickname)) {
            return service.getPlayers().get(nickname);
        } else {
            throw new NotFoundException(Message.ERROR_PLAYER_NOT_FOUND.getMessage());
        }
    }

    @Override
    public boolean deletePlayer(String nickname) {
        nickname = nickname.toLowerCase();

        Player player = getPlayer(nickname);

        if (Objects.nonNull(player)) {
            service.getPlayers().remove(nickname);
            return true;
        } else {
            throw new NotFoundException(Message.ERROR_PLAYER_NOT_FOUND.getMessage());
        }
    }

    @Override
    public Map<String, Player> getPlayers() {
        return service.getPlayers();
    }
}
