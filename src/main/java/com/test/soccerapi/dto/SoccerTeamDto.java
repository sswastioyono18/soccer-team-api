package com.test.soccerapi.dto;

import com.test.soccerapi.entity.Player;
import com.test.soccerapi.entity.Team;
import lombok.Data;
import lombok.ToString;

import java.util.List;


@Data
@ToString
public class SoccerTeamDto {
    private Team team;

    private List<Player> players;
}
