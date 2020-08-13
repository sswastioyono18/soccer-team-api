package com.test.soccerapi.service;

import com.test.soccerapi.entity.Player;

import java.util.List;

public interface PlayerService {
    List<Player> findByTeamName(String teamName);
    List<Player> findAll();
}
