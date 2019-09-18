package com.example.webjackpot.repositories;

import com.example.webjackpot.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {
    Optional<Player> findByNameAndSurname(String name, String surname);
}
