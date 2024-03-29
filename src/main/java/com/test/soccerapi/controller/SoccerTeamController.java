package com.test.soccerapi.controller;

import com.test.soccerapi.entity.Player;
import com.test.soccerapi.entity.Team;
import com.test.soccerapi.exception.RecordNotFoundException;
import com.test.soccerapi.service.PlayerService;
import com.test.soccerapi.service.TeamService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/soccer", produces = { MediaType.APPLICATION_JSON_VALUE})
public class SoccerTeamController {

    private final TeamService teamService;
    private final PlayerService playerService;

    @Autowired
    public SoccerTeamController(final TeamService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }


    @GetMapping("/players/{teamName}")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retrieve all players based on team name") })
    @ResponseBody
    public ResponseEntity<List<Player>> findByTeamName(@PathVariable String teamName) {
        List<Player> playerList = playerService.findByTeamName(teamName);
        if(playerList.isEmpty()) {
            throw new RecordNotFoundException("There is no player in team " +teamName+ " or invalid team name");
        }
        return new ResponseEntity<>(playerList, HttpStatus.OK);
    }

    @GetMapping("/players")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retrieve all players (without team name)") })
    @ResponseBody
    public ResponseEntity<List<Player>> findAll() {
        return new ResponseEntity<>(playerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/teams")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retrieve all teams and its players") })
    @ResponseBody
    public ResponseEntity<List<Team>> findAllTeamsAndPlayers() {
        return new ResponseEntity<>(teamService.findAllTeamsAndPlayers(), HttpStatus.OK);
    }


    @PostMapping("/team")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Create a team with (or without) player(s)") })
    @ResponseBody
    public ResponseEntity addTeam(@Valid @RequestBody Team team){
        teamService.addTeam(team);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/teams")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Create multiple team with (or without) player(s)") })
    @ResponseBody
    public ResponseEntity addTeams(@Valid @RequestBody List<Team> teamList) {
        teamService.addTeams(teamList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
