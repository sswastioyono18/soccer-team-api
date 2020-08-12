package com.test.soccerapi.controller;

import com.test.soccerapi.dto.SoccerTeamDataDto;
import com.test.soccerapi.entity.Player;
import com.test.soccerapi.entity.Team;
import com.test.soccerapi.service.PlayerService;
import com.test.soccerapi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/soccer")
public class SoccerController {

    private final TeamService teamService;
    private final PlayerService playerService;

    @Autowired
    public SoccerController(final TeamService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @GetMapping("/{teamName}/players")
    @Description("Retrieve all players on a team")
    @ResponseBody
    public ResponseEntity<List<Team>> getPlayers(@PathVariable String teamName) {
        return new ResponseEntity<>(teamService.findByTeamName(teamName), HttpStatus.OK);
    }

    @GetMapping("/players")
    @Description("Retrieve all players")
    @ResponseBody
    public ResponseEntity<List<Player>> getAllPlayers() {
        return new ResponseEntity<>(playerService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/player")
    @Description("Add a player")
    @ResponseBody
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        playerService.addPlayer(player);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/team")
    @Description("Create a team with (or without player(s))")
    @ResponseBody
    public ResponseEntity<Team> createTeam(@RequestBody SoccerTeamDataDto soccerTeamDataDto) {
        Team team = teamService.createTeam(soccerTeamDataDto.getTeam());
        playerService.addPlayers(soccerTeamDataDto.getPlayers(), team);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/teams")
    @Description("Create multiple team with (or without player(s))")
    @ResponseBody
    public ResponseEntity<List<SoccerTeamDataDto>> createTeams(@RequestBody List<SoccerTeamDataDto> soccerTeamDataList) {
        for(SoccerTeamDataDto soccerTeamDataDto: soccerTeamDataList){
            Team team = teamService.createTeam(soccerTeamDataDto.getTeam());
            playerService.addPlayers(soccerTeamDataDto.getPlayers(), team);
        }
        return new ResponseEntity<>(soccerTeamDataList, HttpStatus.CREATED);
    }
}
