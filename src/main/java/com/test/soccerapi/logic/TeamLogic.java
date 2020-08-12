package com.test.soccerapi.logic;


import com.test.soccerapi.entity.Player;
import com.test.soccerapi.entity.Team;

import java.util.List;

public interface TeamLogic {
    List<Team> findAll();

    Team createTeam(Team team);
    void createTeams(List<Team> soccerTeamDataList);
}
