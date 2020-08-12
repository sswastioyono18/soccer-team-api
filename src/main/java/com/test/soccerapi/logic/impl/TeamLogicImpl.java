package com.test.soccerapi.logic.impl;

import com.test.soccerapi.entity.Team;
import com.test.soccerapi.logic.TeamLogic;
import com.test.soccerapi.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeamLogicImpl implements TeamLogic {

    final TeamRepository teamRepository;

    @Autowired
    public TeamLogicImpl(final TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> findByTeamId(Long teamId) {
       return teamRepository.findByTeamId(teamId);
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

}
