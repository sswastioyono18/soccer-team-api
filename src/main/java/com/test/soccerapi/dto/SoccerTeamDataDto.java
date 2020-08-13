package com.test.soccerapi.dto;

import com.test.soccerapi.entity.Player;
import com.test.soccerapi.entity.Team;
import lombok.*;

import java.util.List;


@Data
@ToString
public class SoccerTeamDataDto {
    private Team team;

    private List<Player> players;
}
