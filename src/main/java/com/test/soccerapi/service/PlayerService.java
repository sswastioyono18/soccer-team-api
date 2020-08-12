package com.test.soccerapi.service;

import com.test.soccerapi.entity.Player;
import com.test.soccerapi.entity.Team;

import java.util.List;

public interface PlayerService {
    List<Player> findAll();
    void addPlayers(List<Player> playerList, Team team);
    Player addPlayer(Player player);
}
