package sactio.reminderapi.repository;

import org.junit.After;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MySQLContainer;
import sactio.reminderapi.ActivityApiApplication;
import sactio.reminderapi.entity.ActivityEntity;
import sactio.reminderapi.config.MySQLBaseContainer;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = MySQLBaseContainer.Initializer.class,
        classes = ActivityApiApplication.class)
public class ActivityRepositoryIT {

  @ClassRule
  public static MySQLContainer mysql = MySQLBaseContainer.getInstance();
  @Autowired
  private ActivityRepository activityRepository;

  @After
  public void resetDb() {
    activityRepository.deleteAll();
  }

  @Test
  public void shouldFindActivity() {
    //Given && When
    ActivityEntity activityEntity = new ActivityEntity();
    activityEntity.setActivityId(123);
    activityEntity.setActivityName("Test123");
    activityRepository.save(activityEntity);
    List<ActivityEntity> found = activityRepository.findByActivityId(123);
    assertThat(found.size(), equalTo(1));
  }

}
