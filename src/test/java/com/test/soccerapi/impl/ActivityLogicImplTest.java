package sactio.reminderapi.logic.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sactio.reminderapi.dto.ActivityDto;
import sactio.reminderapi.entity.ActivityEntity;
import sactio.reminderapi.repository.ActivityRepository;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class ActivityLogicImplTest {
    @Mock
    ActivityRepository activityRepository;
    @InjectMocks
    ActivityLogicImpl activityLogicImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByActivityId() throws Exception {
        when(activityRepository.findByActivityId(anyInt())).thenReturn(Arrays.<ActivityEntity>asList(new ActivityEntity()));
        ActivityDto result = activityLogicImpl.findByActivityId(123);
        ActivityDto expected = new ActivityDto();
        expected.setActivityEntityList(Collections.<ActivityEntity>singletonList(new ActivityEntity()));
        Assert.assertEquals(expected, result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme