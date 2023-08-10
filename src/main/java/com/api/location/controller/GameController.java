package com.api.location.controller;

import com.api.location.dto.CreateGameRequest;
import com.api.location.dto.GameInfoResponse;
import com.api.location.entity.MemberGameEntity;
import com.api.location.service.GameService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/api")
public class GameController {

    private final GameService gameService;

    @PostMapping("/game")
    public void createGame(@RequestBody CreateGameRequest req){
        gameService.createGame(req);
    }

    @PostMapping("/game/{gameId}/join")
    public void joinGame(
            @PathVariable(name = "gameId") Long gameId,
            @RequestBody Long memberId){

        gameService.joinGame(gameId,memberId);
    }

    @GetMapping("/game/{gameId}/info")
    public GameInfoResponse getGameInfo(
            @PathVariable(name = "gameId") Long gameId){

        return gameService.getGameInfo(gameId);
    }
}
