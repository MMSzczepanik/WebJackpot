package com.example.webjackpot.controllers;

import com.example.webjackpot.model.dto.PlayerDto;
import com.example.webjackpot.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "addPlayer")
    public ResponseEntity<PlayerDto> addPlayer(@RequestBody PlayerDto playerDto){
        PlayerDto playerDtoTmp = playerService.save(playerDto);
        if(playerDto != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(playerDtoTmp,HttpStatus.CREATED);
        }
    }

}
