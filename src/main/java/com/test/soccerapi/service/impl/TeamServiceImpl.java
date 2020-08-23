package com.test.soccerapi.service.impl;

import com.test.soccerapi.entity.Team;
import com.test.soccerapi.repository.TeamRepository;
import com.test.soccerapi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    @Override
    public List<Team> findAllTeamsAndPlayers() {
        return teamRepository.findAll();
    }


    @Override
    public void addTeam(Team team)  {
        assignTeamAndSave(team);
    }

    @Override
    public void addTeams(List<Team> teamList) {
        for(Team team: teamList){
            assignTeamAndSave(team);
        }
    }

    private void assignTeamAndSave(Team team){
        if (team.getId() != null) {
            team.getPlayers().forEach(player -> player.setTeam(team));
            teamRepository.save(team);
        }
    }
}
