package sactio.reminderapi.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sactio.reminderapi.dto.ActivityDto;
import sactio.reminderapi.entity.ActivityEntity;
import sactio.reminderapi.logic.ActivityLogic;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class ActivityServiceImplTest {
    @Mock
    ActivityLogic activityLogic;
    @InjectMocks
    ActivityServiceImpl activityServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetListActivityId() throws Exception {
        when(activityLogic.findByActivityId(anyInt())).thenReturn(new ActivityDto());

        ActivityDto result = activityServiceImpl.getActivityById(123);
        ActivityDto expected = new ActivityDto();
        expected.setActivityEntityList(Collections.singletonList(new ActivityEntity()));
        Assert.assertEquals(new ActivityDto(), result);
    }

    @Test
    public void testGetAllActivities() throws Exception {
        when(activityLogic.findAllActivities()).thenReturn(new ActivityDto());

        ActivityDto result = activityServiceImpl.getAllActivities();
        ActivityDto expected = new ActivityDto();
        expected.setActivityEntityList(Collections.singletonList(new ActivityEntity()));
        Assert.assertEquals(new ActivityDto(), result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme