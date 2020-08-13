package com.test.soccerapi.controller;

import com.test.soccerapi.dto.SoccerTeamDto;
import com.test.soccerapi.entity.Player;
import com.test.soccerapi.entity.Team;
import com.test.soccerapi.service.PlayerService;
import com.test.soccerapi.service.TeamService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/soccer")
public class SoccerTeamController {

    private final TeamService teamService;
    private final PlayerService playerService;

    @Autowired
    public SoccerTeamController(final TeamService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }


    @GetMapping("/players/team/{teamName}")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retrieve all players based on team name") })
    @ResponseBody
    public ResponseEntity<List<Player>> getPlayersByTeamName(@PathVariable String teamName) {
        return new ResponseEntity<>(playerService.findByTeamName(teamName), HttpStatus.OK);
    }

    @GetMapping("/players/team")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retrieve all players") })
    @ResponseBody
    public ResponseEntity<List<Player>> getAllPlayers() {
        return new ResponseEntity<>(playerService.findAll(), HttpStatus.OK);
    }


    @PostMapping("/team")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Create a team with (or without player(s))") })
    @ResponseBody
    public ResponseEntity<Team> createTeam(@RequestBody SoccerTeamDto soccerTeamDataDto ) {
        Team team = teamService.createTeam(soccerTeamDataDto.getTeam());
        playerService.addPlayers(soccerTeamDataDto.getPlayers(), team);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/teams")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Create multiple team with (or without player(s))") })
    @ResponseBody
    public ResponseEntity<List<SoccerTeamDto>> createTeams(@RequestBody List<SoccerTeamDto> soccerTeamDataList) {
        for(SoccerTeamDto soccerTeamDataDto: soccerTeamDataList){
            Team team = teamService.createTeam(soccerTeamDataDto.getTeam());
            playerService.addPlayers(soccerTeamDataDto.getPlayers(), team);
        }
        return new ResponseEntity<>(soccerTeamDataList, HttpStatus.CREATED);
    }
}
