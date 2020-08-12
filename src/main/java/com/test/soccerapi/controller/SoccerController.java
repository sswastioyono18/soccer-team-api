package com.test.soccerapi.controller;

import com.test.soccerapi.entity.Player;
import com.test.soccerapi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/soccer")
public class SoccerController {

    private final TeamService teamService;

    @Autowired
    public SoccerController(final TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/players/{teamId}")
    @ResponseBody
    public ResponseEntity<List<Player>> getActivity(@PathVariable String teamId) {
        return new ResponseEntity<>(teamService.findByTeamId(teamId), HttpStatus.OK);
    }
}
