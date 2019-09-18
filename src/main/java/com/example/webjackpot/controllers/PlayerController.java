package com.example.webjackpot.controllers;

import com.example.webjackpot.model.dto.PlayerDto;
import com.example.webjackpot.model.entity.Player;
import com.example.webjackpot.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "addPlayer")
    public ResponseEntity<Player> addPlayer(@RequestBody PlayerDto playerDto){
        Player playerTmp = playerService.save(playerDto);
        if(playerDto != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(playerTmp,HttpStatus.CREATED);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "fetchAllPlayers")
    public ResponseEntity<List<Player>> fetchAllPlayers(){
        return new ResponseEntity<>(playerService.fetchAllPlayers(),HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "deletePlayer/{id}")
    public HttpStatus deletePlayer(@PathVariable Long id){
        return this.playerService.deletePlayer(id);
    }

}
