package org.example.sandship.controller;

import jakarta.validation.Valid;
import org.example.sandship.helper.ErrorHelper;
import org.example.sandship.persistence.model.Player;
import org.example.sandship.persistence.request.PlayerRequest;
import org.example.sandship.service.player.impl.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    PlayerServiceImpl service;

    @PostMapping("/create")
    public ResponseEntity<?> createPlayer(@Valid @RequestBody PlayerRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.unprocessableEntity()
                    .body(ErrorHelper.getErrors(result));
        }

        Player player = service.createPlayer(request.getNickname());

        return ResponseEntity.ok(player);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getPlayer(@Valid @RequestBody PlayerRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.unprocessableEntity()
                    .body(ErrorHelper.getErrors(result));
        }

        Player player = service.getPlayer(request.getNickname());

        return ResponseEntity.ok(player);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deletePlayer(@Valid @RequestBody PlayerRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.unprocessableEntity()
                    .body(ErrorHelper.getErrors(result));
        }

        boolean deleted = service.deletePlayer(request.getNickname());

        return ResponseEntity.ok(deleted);
    }

    @GetMapping()
    public ResponseEntity<?> getPlayers() {
        Map<String, Player> players = service.getPlayers();

        return ResponseEntity.ok(players);
    }
}
