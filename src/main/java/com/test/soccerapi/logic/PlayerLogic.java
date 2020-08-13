package com.test.soccerapi.logic;


import com.test.soccerapi.entity.Player;

import java.util.List;

public interface PlayerLogic {
    List<Player> findByTeamName(String teamId);
    List<Player> findAll();
}
