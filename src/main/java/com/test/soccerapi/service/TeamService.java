package com.test.soccerapi.service;


import com.test.soccerapi.entity.Player;

import java.util.List;

public interface TeamService {
    List<Player> findByTeamId(String teamId);
}
