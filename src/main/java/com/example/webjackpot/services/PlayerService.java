package com.example.webjackpot.services;

import com.example.webjackpot.model.dto.PlayerDto;
import com.example.webjackpot.model.entity.Player;
import com.example.webjackpot.repositories.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public PlayerDto save(PlayerDto playerDto) {
        Optional<Player> optionalPlayer = playerRepository.findByNameAndSurname(playerDto.getName(), playerDto.getSurname());
        if(optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            if (playerDto.getName() != null) player.setName(playerDto.getName());
            if (playerDto.getSurname() != null) player.setSurname(playerDto.getSurname());
            if (playerDto.getNickName() != null) player.setNickName(playerDto.getNickName());
            if (playerDto.getAge() != 0) player.setAge(playerDto.getAge());
            return mapper.map(playerRepository.save(player),PlayerDto.class);
        }

        return mapper.map(playerRepository.save(mapper.map(playerDto,Player.class)),PlayerDto.class);

    }
}
