package com.test.soccerapi.logic;


import com.test.soccerapi.entity.Team;

import java.util.List;

public interface TeamLogic {
    List<Team> findAll();
    Team addTeam(Team team);
}
