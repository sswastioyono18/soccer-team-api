package com.test.soccerapi.logic.impl;

import com.test.soccerapi.entity.Player;
import com.test.soccerapi.entity.Team;
import com.test.soccerapi.repository.TeamRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class TeamLogicImplTest {
    @Mock
    TeamRepository teamRepository;
    @InjectMocks
    TeamLogicImpl teamLogicImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void when_teamIdIsNull_saveIsNotCalled() {
        Team team = new Team();
        team.setId(null);
        team.setTeamName("Test Team Name");
        teamLogicImpl.addTeam(team);
        verify(teamRepository, never()).save(team);
    }

    @Test
    public void when_teamIdIsNotNull_saveIsCalled() {
        Team team = new Team();
        Player player = new Player();
        player.setId(123L);
        player.setPlayerName("test");
        player.setPlayerNo("8");
        player.setTeam(team);
        team.setId(123L);
        team.setTeamName("Test Team Name");
        team.setPlayers(Arrays.asList(player));
        teamLogicImpl.addTeam(team);
        verify(teamRepository, Mockito.times(1)).save(team);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme