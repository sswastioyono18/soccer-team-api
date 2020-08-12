package com.test.soccerapi.service;


import com.test.soccerapi.entity.Player;
import com.test.soccerapi.entity.Team;

import java.util.List;

public interface PlayerService {
    List<Player> findByPlayerName(String teamName);
    List<Player> findAll();
    Team addPlayer(Player player);
    void addPlayers(List<Player> playerList);
}
