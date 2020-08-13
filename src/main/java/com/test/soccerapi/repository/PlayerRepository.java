package com.test.soccerapi.repository;

import com.test.soccerapi.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
    List<Player> findByTeam_TeamName(String teamName);
}
