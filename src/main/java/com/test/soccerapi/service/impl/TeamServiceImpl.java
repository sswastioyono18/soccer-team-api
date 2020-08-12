package com.test.soccerapi.service.impl;

import com.test.soccerapi.entity.Player;
import com.test.soccerapi.logic.TeamLogic;
import com.test.soccerapi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamLogic teamLogic;

    @Autowired
    public TeamServiceImpl(TeamLogic teamLogic) {
        this.teamLogic = teamLogic;
    }

    @Override
    public List<Player> findByTeamId(String teamId) {
        return teamLogic.findByTeamId(teamId);
    }
}
