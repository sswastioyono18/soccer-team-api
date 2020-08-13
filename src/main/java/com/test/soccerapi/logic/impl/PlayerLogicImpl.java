package com.test.soccerapi.logic.impl;

import com.test.soccerapi.entity.Player;
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

    public List<Player> findByTeamName(String teamName) {
        return playerRepository.findByTeam_TeamName(teamName);
    }

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

}
