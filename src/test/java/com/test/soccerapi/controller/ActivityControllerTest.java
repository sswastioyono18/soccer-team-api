package sactio.reminderapi.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sactio.reminderapi.dto.ActivityDto;
import sactio.reminderapi.dto.ActivityRequestDto;
import sactio.reminderapi.dto.ActivityResponseDto;
import sactio.reminderapi.service.impl.ActivityServiceImpl;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class ActivityControllerTest {
    @Mock
    ActivityServiceImpl activityServiceImpl;
    @InjectMocks
    ActivityController activityController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetActivity() throws Exception {
        when(activityServiceImpl.getActivityById(anyInt())).thenReturn(new ActivityDto());

        ActivityResponseDto<ActivityDto> result = activityController.getActivity(anyInt());
        ActivityResponseDto<ActivityDto> expected = new ActivityResponseDto<>();
        expected.setResponseCode(200);
        expected.setMessage("Success");
        expected.setData(new ActivityDto());
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testGetAllActivity() throws Exception {
        when(activityServiceImpl.getAllActivities()).thenReturn(new ActivityDto());

        ActivityResponseDto<ActivityDto> result = activityController.getAllActivity();
        ActivityResponseDto<ActivityDto> expected = new ActivityResponseDto<>();
        expected.setResponseCode(200);
        expected.setMessage("Success");
        expected.setData(new ActivityDto());
        Assert.assertEquals(expected, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme