package com.test.soccerapi.logic.impl;

import com.test.soccerapi.entity.Player;
import com.test.soccerapi.logic.TeamLogic;
import com.test.soccerapi.repository.TeamRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class TeamLogicImpl implements TeamLogic {

    final TeamRepository teamRepository;

    public TeamLogicImpl(final TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Player> findByTeamId(String teamId) {
       return teamRepository.findByTeamId(teamId);
    }

}
