package com.test.soccerapi.service;


import com.test.soccerapi.entity.Team;

import java.util.List;

public interface TeamService {
    List<Team> findByTeamId(Long teamId);
    List<Team> findAll();
}
