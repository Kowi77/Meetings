package kov.develop.controller;

import kov.develop.model.MeetingForUi;
import kov.develop.dataTest.*;

 import org.junit.Assert;
import org.junit.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

public class MainRestControllerTest extends AbstractControllerTest {

    static final String REST_URL = "/meetings";

    @Test
    public void testGetAll() throws Exception {
        ResultActions resultActions = mockMvc.perform(get(REST_URL));
        List<MeetingForUi> result = checkAndGetMeetings(resultActions);
        Assert.assertArrayEquals(DataTest.MEETING_FOR_UIS, result.toArray());
    }

    @Test
    public void testFilterByDepart() throws Exception {
        ResultActions resultActions  = mockMvc.perform(get(REST_URL + "/depart/2"));
        List<MeetingForUi> result = checkAndGetMeetings(resultActions);
        MeetingForUi[] testData = {DataTest.MEETING_FOR_UIS[4], DataTest.MEETING_FOR_UIS[5]};
        Assert.assertArrayEquals(testData, result.toArray());
    }

    @Test
    public void testFilterByEmployer() throws Exception {
        ResultActions resultActions  = mockMvc.perform(get(REST_URL + "/employer/1"));
        List<MeetingForUi> result = checkAndGetMeetings(resultActions);
        MeetingForUi[] testData = {DataTest.MEETING_FOR_UIS[1]};
        Assert.assertArrayEquals(testData, result.toArray());
    }

    @Test
    public void testFilterByMember() throws Exception {
        ResultActions resultActions  = mockMvc.perform(get(REST_URL + "/member/1"));
        List<MeetingForUi> result = checkAndGetMeetings(resultActions);
        MeetingForUi[] testData = {DataTest.MEETING_FOR_UIS[3], DataTest.MEETING_FOR_UIS[5]};
        Assert.assertArrayEquals(testData, result.toArray());
    }

    @Test
    public void testFilterByDate() throws Exception {
        ResultActions resultActions  = mockMvc.perform(post(REST_URL + "/date")
                .param("startDate", "2017-05-01T12:12:12")
                .param("endDate", "2017-08-01T12:12:12"));
        List<MeetingForUi> result = checkAndGetMeetings(resultActions);
        MeetingForUi[] testData = {DataTest.MEETING_FOR_UIS[4], DataTest.MEETING_FOR_UIS[5]};
        Assert.assertArrayEquals(testData, result.toArray());
    }
}
