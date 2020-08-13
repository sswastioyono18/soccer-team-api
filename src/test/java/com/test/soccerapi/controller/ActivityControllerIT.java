package sactio.reminderapi.controller;

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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import sactio.reminderapi.ActivityApiApplication;
import sactio.reminderapi.config.MySQLBaseContainer;
import sactio.reminderapi.dto.ActivityDto;
import sactio.reminderapi.dto.ActivityResponseDto;
import sactio.reminderapi.entity.ActivityEntity;
import sactio.reminderapi.repository.ActivityRepository;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ActivityApiApplication.class)
@ContextConfiguration(initializers = MySQLBaseContainer.Initializer.class,
        classes = ActivityApiApplication.class)
@AutoConfigureMockMvc
public class ActivityControllerIT {


    @ClassRule
    public static MySQLContainer mysql = MySQLBaseContainer.getInstance();
    @Autowired
    private ActivityRepository activityRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mvc;


    private String drinkCoffeeActivity = "drinkCoffee";
    private String eatTakoyakiActivity = "eatTakoyaki";

    @After
    public void resetDb() {
        activityRepository.deleteAll();
    }


    @Test
    @Sql("/sql/IT/activity_data.sql")
    public void testGetAllActivities_return200_and_verifyResult() {
        ActivityResponseDto<ActivityDto> activityResponseDto = this.restTemplate.exchange("http://localhost:" + port + "/api/activity/get", HttpMethod.GET, null, new ParameterizedTypeReference<ActivityResponseDto<ActivityDto>>() {
        }).getBody();
        assertThat(activityResponseDto.getResponseCode(), is(200));
        assertThat(activityResponseDto.getData().getActivityEntityList().get(0).getActivityName(), is(drinkCoffeeActivity));
        assertThat(activityResponseDto.getData().getActivityEntityList().get(1).getActivityName(), is(eatTakoyakiActivity));

    }

    @Test
    @Sql("/sql/IT/activity_data.sql")
    public void givenActivity_shouldReturn_200_and_containActivity() throws Exception {

        mvc.perform(get("/api/activity/get").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString(eatTakoyakiActivity)))
                .andExpect(content().string(containsString(drinkCoffeeActivity)));

    }

    @Test
    @Sql("/sql/IT/activity_data.sql")
    public void givenActivity_shouldReturn_200_and_jsonShouldMatch() throws Exception {

        mvc.perform(get("/api/activity/get").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{'responseCode':200,'message':'Success','data':{'activityEntityList':[{'activityId':1,'activityName':'drinkCoffee'},{'activityId':2,'activityName':'eatTakoyaki'}]}})"));

    }

    @Test
    @Sql("/sql/IT/activity_data.sql")
    public void givenActivity_whenGetActivity_shouldReturn() throws Exception {

        mvc.perform(get("/api/activity/get/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{'responseCode':200,'message':'Success','data':{'activityEntityList':[{'activityId':1,'activityName':'drinkCoffee'}]}})"));


        mvc.perform(get("/api/activity/get/2").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{'responseCode':200,'message':'Success','data':{'activityEntityList':[{'activityId':2,'activityName':'eatTakoyaki'}]}})"));

    }

    @Test
    @Sql("/sql/IT/activity_data.sql")
    public void givenActivity_whenGetActivity_notExist_shouldReturnEmpty() throws Exception {

        mvc.perform(get("/api/activity/get/3").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{'responseCode':200,'message':'Success','data':{'activityEntityList':[]}})"));

    }

    @Test
    @Sql("/sql/IT/activity_data.sql")
    public void insertActivity_shouldUpdateIfRecordExist_andReturnSuccess() throws Exception {
        String jsonString = new JSONObject()
                .put("activityId", 1)
                .put("activityName", "new activity")
                .toString();

        mvc.perform(post("/api/activity/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(jsonString))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString(jsonString)));

        mvc.perform(get("/api/activity/get").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{'responseCode':200,'message':'Success','data':{'activityEntityList':[{'activityId':1,'activityName':'new activity'},{'activityId':2,'activityName':'eatTakoyaki'}]}})"));


    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme