package com.test.soccerapi.logic;


import com.test.soccerapi.entity.Team;

import java.util.List;

public interface TeamLogic {
    List<Team> findByTeamId(Long teamId);
    List<Team> findAll();
}
