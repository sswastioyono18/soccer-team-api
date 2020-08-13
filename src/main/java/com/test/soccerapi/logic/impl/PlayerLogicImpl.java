package com.test.soccerapi.logic.impl;

import com.test.soccerapi.entity.Player;
import com.test.soccerapi.entity.Team;
import com.test.soccerapi.logic.PlayerLogic;
import com.test.soccerapi.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerLogicImpl implements PlayerLogic {

    final PlayerRepository playerRepository;

    @Autowired
    public PlayerLogicImpl(final PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> findByTeamId(Long teamId) {
        return playerRepository.findByTeam_TeamId(teamId);
    }

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public void addPlayers(List<Player> playerList, Team team) {
        for(Player player: playerList){
            player.setTeam(team);
            playerRepository.save(player);
        }
    }
}
