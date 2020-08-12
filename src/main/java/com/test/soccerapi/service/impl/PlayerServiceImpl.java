package com.test.soccerapi.service.impl;

import com.test.soccerapi.entity.Player;
import com.test.soccerapi.entity.Team;
import com.test.soccerapi.logic.PlayerLogic;
import com.test.soccerapi.service.PlayerService;
import com.test.soccerapi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerLogic playerLogic;

    @Autowired
    public PlayerServiceImpl(PlayerLogic playerLogic) {
        this.playerLogic = playerLogic;
    }

    @Override
    public List<Player> findByPlayerName(String teamName) {
        return null;
    }

    @Override
    public List<Player> findAll() {
        return null;
    }

    @Override
    public Team addPlayer(Player player) {
        return null;
    }

    @Override
    public void addPlayers(List<Player> playerList) {

    }
}
