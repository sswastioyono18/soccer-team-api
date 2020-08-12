package com.test.soccerapi.logic;


import com.test.soccerapi.entity.Player;
import com.test.soccerapi.entity.Team;

import java.util.List;

public interface PlayerLogic {
    List<Team> findAll();

    Player addPlayer(Player player);
    void addPlayers(List<Player> playerList);
}
