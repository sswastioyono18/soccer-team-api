package com.test.soccerapi.logic;


import com.test.soccerapi.entity.Player;

import java.util.List;

public interface TeamLogic {
    List<Player> findByTeamId(String teamId);
}
