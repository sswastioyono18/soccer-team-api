package com.test.soccerapi.logic;


import com.test.soccerapi.entity.Player;
import com.test.soccerapi.entity.Team;

import java.util.List;

public interface PlayerLogic {

    List<Player> findByTeamId(Long teamId);
    List<Player> findAll();
    Player addPlayer(Player player);
    void addPlayers(List<Player> playerList, Team team);
}
