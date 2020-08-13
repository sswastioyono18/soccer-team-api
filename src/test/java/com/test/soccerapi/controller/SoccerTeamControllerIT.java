package com.test.soccerapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.soccerapi.SoccerApiApplication;
import com.test.soccerapi.config.MySQLBaseContainer;
import com.test.soccerapi.repository.PlayerRepository;
import com.test.soccerapi.repository.TeamRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SoccerApiApplication.class)
@ContextConfiguration(initializers = MySQLBaseContainer.Initializer.class,
        classes = SoccerApiApplication.class)
@AutoConfigureMockMvc
public class SoccerTeamControllerIT {

    @ClassRule
    public static MySQLContainer mysql = MySQLBaseContainer.getInstance();
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;



    @After
    public void resetDb() {
        teamRepository.deleteAll();
        playerRepository.deleteAll();
    }

    @Test
    public void test_createTeam_then_getAllPlayers_and_getPlayersByTeam_shouldReturnSuccess() throws Exception {

        JSONArray array = new JSONArray();
        JSONObject player = new JSONObject();
        JSONObject player2 = new JSONObject();
        //given the player
        player.put("id", 100L)
                .put("playerName", "Steven Gerrard")
                .put("playerNo", "8");

        player2.put("id", 101L)
                .put("playerName", "Xabi Alonso")
                .put("playerNo", "6");

        array.put(player);
        array.put(player2);

        //given the team
        String jsonString = new JSONObject()
                .put("id", 100L)
                .put("teamName", "Liverpool FC")
                .put("players", array)
                .toString();


        // do post team with players
        mvc.perform(post("/api/soccer/team")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(jsonString))
                .andExpect(status().isCreated());

        // check get players endpoint
        mvc.perform(get("/api/soccer/players")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].playerName").value("Steven Gerrard"))
                .andExpect(jsonPath("$[1].playerName").value("Xabi Alonso"));

        // check get players for team endpoint
        mvc.perform(get("/api/soccer/players/{teamName}","Liverpool FC")
                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].playerName").value("Steven Gerrard"))
                .andExpect(jsonPath("$[1].playerName").value("Xabi Alonso"));
    }

    @Test
    public void test_createTeams_then_getAllPlayers_and_getPlayersByTeam_shouldReturnSuccess() throws Exception {
        JSONArray arrayLiverpool = new JSONArray();
        JSONArray arrayChelsea = new JSONArray();
        JSONObject player = new JSONObject();
        JSONObject player2 = new JSONObject();
        JSONObject player3 = new JSONObject();

        // given the player
        player.put("id", 100L)
                .put("playerName", "Steven Gerrard")
                .put("playerNo", "8");

        player2.put("id", 101L)
                .put("playerName", "Xabi Alonso")
                .put("playerNo", "6");

        player3.put("id", 102L)
                .put("playerName", "Frank Lampard")
                .put("playerNo", "8");

        arrayLiverpool.put(player);
        arrayLiverpool.put(player2);
        arrayChelsea.put(player3);

        // given the team
        String jsonString = new JSONObject()
                .put("id", 100L)
                .put("teamName", "Liverpool FC")
                .put("players", arrayLiverpool)
                .toString();

        String jsonString2 = new JSONObject()
                .put("id", 101L)
                .put("teamName", "Chelsea FC")
                .put("players", arrayChelsea)
                .toString();

        List<String> jsonStringList = new ArrayList<>();
        jsonStringList.add(jsonString);
        jsonStringList.add(jsonString2);

        // not scaling but it works
        // String content = "["+jsonString+","+jsonString2+"]";

        // do post teams with players
        mvc.perform(post("/api/soccer/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content(fromListStringToJson(jsonStringList)))
                .andExpect(status().isCreated())
                .andReturn();;

        mvc.perform(get("/api/soccer/players")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].playerName").value("Steven Gerrard"))
                .andExpect(jsonPath("$[1].playerName").value("Xabi Alonso"))
                .andExpect(jsonPath("$[2].playerName").value("Frank Lampard"));


        mvc.perform(get("/api/soccer/players/{teamName}","Chelsea FC")
                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].playerName").value("Frank Lampard"));
    }

    private String fromListStringToJson(List<String> jsonStringList) throws JsonProcessingException {
        return objectMapper.writeValueAsString(jsonStringList).replace("}\",\"{","},{").replace("\\","").replace("[\"{","[{").replace("}\"]","}]");
    }

    @Test
    public void test_createTeams_then_getAllTeamsAndPlayers_shouldReturnSuccess() throws Exception {

        JSONArray arrayLiverpool = new JSONArray();
        JSONArray arrayChelsea = new JSONArray();
        JSONObject player = new JSONObject();
        JSONObject player2 = new JSONObject();
        JSONObject player3 = new JSONObject();
        // given the player

        player.put("id", 100L)
                .put("playerName", "Steven Gerrard")
                .put("playerNo", "8");

        player2.put("id", 101L)
                .put("playerName", "Xabi Alonso")
                .put("playerNo", "6");

        player3.put("id", 102L)
                .put("playerName", "Frank Lampard")
                .put("playerNo", "8");

        arrayLiverpool.put(player);
        arrayLiverpool.put(player2);
        arrayChelsea.put(player3);

        // given the team
        String jsonString = new JSONObject()
                .put("id", 100L)
                .put("teamName", "Liverpool FC")
                .put("players", arrayLiverpool)
                .toString();

        String jsonString2 = new JSONObject()
                .put("id", 101L)
                .put("teamName", "Chelsea FC")
                .put("players", arrayChelsea)
                .toString();

        List<String> jsonStringList = new ArrayList<>();
        jsonStringList.add(jsonString);
        jsonStringList.add(jsonString2);

        // not scaling but it works
        // String content = "["+jsonString+","+jsonString2+"]";

        // do post teams with players
        mvc.perform(post("/api/soccer/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content(fromListStringToJson(jsonStringList)))
                .andExpect(status().isCreated())
                .andReturn();


        mvc.perform(get("/api/soccer/teams").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].teamName").value("Liverpool FC"))
                .andExpect(jsonPath("$[1].teamName").value("Chelsea FC"))
                .andExpect(jsonPath("$.[0].players[0].playerName").value("Steven Gerrard"))
                .andExpect(jsonPath("$.[0].players[1].playerName").value("Xabi Alonso"))
                .andExpect(jsonPath("$.[1].players[0].playerName").value("Frank Lampard"));
    }
}