package com.test.soccerapi.logic;


import com.test.soccerapi.entity.Team;

import java.util.List;

public interface TeamLogic {
    List<Team> findByTeamName(String teamName);
    List<Team> findAll();

    Team createTeam(Team team);
    void createTeams(List<Team> soccerTeamDataList);
}
