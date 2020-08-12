package com.test.soccerapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.test.soccerapi.entity.Player;
import com.test.soccerapi.entity.Team;
import lombok.*;

import javax.persistence.Entity;
import java.util.List;
import java.util.Map;


@Data
@ToString
public class SoccerTeamDataDto {
    private Team team;

    private List<Player> players;
}
