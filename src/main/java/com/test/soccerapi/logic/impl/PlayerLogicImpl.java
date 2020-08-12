package com.test.soccerapi.logic.impl;

import com.test.soccerapi.entity.Player;
import com.test.soccerapi.entity.Team;
import com.test.soccerapi.logic.PlayerLogic;
import com.test.soccerapi.logic.TeamLogic;
import com.test.soccerapi.repository.PlayerRepository;
import com.test.soccerapi.repository.TeamRepository;
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


    @Override
    public List<Team> findAll() {
        return null;
    }

    @Override
    public Player addPlayer(Player player) {
        return null;
    }

    @Override
    public void addPlayers(List<Player> playerList) {

    }
}
