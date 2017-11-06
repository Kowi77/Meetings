package kov.develop.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import kov.develop.dataTest.*;
import kov.develop.model.EmployerForUi;
import kov.develop.model.MeetingForUi;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FormControllerTest extends AbstractControllerTest {

    static final String REST_URL = "/meeting";

    @Test
    public void testGetAllMembersByMeet() throws Exception {
        ResultActions resultActions = mockMvc.perform(get(REST_URL + "/membersByMeet/2"));
        List<EmployerForUi> result = checkAndGetEmployers(resultActions);
        EmployerForUi[] testData = {DataTest.EMPLOYER_FOR_UIS[4], DataTest.EMPLOYER_FOR_UIS[5]};
        Assert.assertArrayEquals(testData, result.toArray());
    }

    @Test
    public void testGetEmployerForUiById() throws Exception {
        MvcResult result = mockMvc.perform(get(REST_URL + "/employerForUiById/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        String resultAsString = result.getResponse().getContentAsString();
        EmployerForUi emp = mapper.readValue(resultAsString, new TypeReference<EmployerForUi>(){});
        Assert.assertEquals(DataTest.EMPLOYER_FOR_UIS[1], emp);
    }

    @Test
    public void testSaveMeeting() throws Exception {
        MvcResult result = mockMvc.perform(post(REST_URL)
                .param("id", "1")
                .param("theme", "Огромное увеличение продаж")
                .param("date", "2017-10-28T13:00:00")
                .param("depart", "4")
                .param("selectDepart", "4")
                .param("employer", "2")
                .param("selectEmployer", "2")
                .param("mems[]", "2, 4, 8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals(DataTest.SAVED_MEETING, meetingService.get(1));
    }


}
