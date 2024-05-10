package org.example.sandship.service.player;

import org.example.sandship.persistence.model.Player;

import java.util.Map;

public interface PlayerService {

    Player createPlayer(String nickname);

    Player getPlayer(String nickname);

    boolean deletePlayer(String nickname);

    Map<String, Player> getPlayers();
}
