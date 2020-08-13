package com.test.soccerapi.service.impl;

import com.test.soccerapi.entity.Player;
import com.test.soccerapi.logic.PlayerLogic;
import com.test.soccerapi.service.PlayerService;
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
    public List<Player> findByTeamName(String teamName) {
        return playerLogic.findByTeamName(teamName);
    }

    @Override
    public List<Player> findAll() {
        return playerLogic.findAll();
    }
}
