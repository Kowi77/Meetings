package kov.develop.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import kov.develop.model.MeetingForUi;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MainRestControllerTest extends AbstractControllerTest {

    static final String REST_URL = "/meetings";

    @Test
    public void testGetAll() throws Exception {
        MvcResult result = mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        String resultAsString = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        List<MeetingForUi> list = mapper.readValue(resultAsString, new TypeReference<List<MeetingForUi>>(){});
        list.forEach(System.out::println);
    }

}
