package com.test.soccerapi.service;


import com.test.soccerapi.entity.Player;
import com.test.soccerapi.entity.Team;

import java.util.List;

public interface TeamService {
    List<Team> findAll();
    Team createTeam(Team team);
    void createTeams(List<Team> teamList);
}
