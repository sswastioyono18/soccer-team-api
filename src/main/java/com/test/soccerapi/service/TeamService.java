package com.test.soccerapi.service;


import com.test.soccerapi.entity.Team;

import java.util.List;

public interface TeamService {
    List<Team> findAllTeamsAndPlayers();
    void addTeam(Team team);
    void addTeams(List<Team> teamList);
}
