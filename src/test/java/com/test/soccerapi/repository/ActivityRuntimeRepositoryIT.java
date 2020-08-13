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
import sactio.reminderapi.config.MySQLBaseContainer;
import sactio.reminderapi.entity.ActivityRuntimeEntity;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = MySQLBaseContainer.Initializer.class,
        classes = ActivityApiApplication.class)
public class ActivityRuntimeRepositoryIT {

  @ClassRule
  public static MySQLContainer mysql = MySQLBaseContainer.getInstance();
  @Autowired
  private ActivityRuntimeRepository activityRuntimeRepository;

  @After
  public void resetDb() {
    activityRuntimeRepository.deleteAll();
  }

  @Test
  public void shouldFindActivityRuntime() {
    //Given && When
    ActivityRuntimeEntity activityRuntimeEntity = new ActivityRuntimeEntity();
    activityRuntimeEntity.setActivityId(123);
    activityRuntimeEntity.setStartTime("1700");
    activityRuntimeEntity.setEndTime("1800");
    activityRuntimeRepository.save(activityRuntimeEntity);

    List<ActivityRuntimeEntity> found = activityRuntimeRepository.findByActivityId(123);
    assertThat(found.size(), equalTo(1));

  }

}
