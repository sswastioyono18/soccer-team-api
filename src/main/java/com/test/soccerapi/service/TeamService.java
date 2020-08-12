package com.test.soccerapi.service;


import com.test.soccerapi.dto.SoccerTeamDataDto;
import com.test.soccerapi.entity.Team;

import java.util.List;

public interface TeamService {
    List<Team> findByTeamName(String teamName);
    List<Team> findAll();
    Team createTeam(Team team);
    void createTeams(List<Team> teamList);
}
