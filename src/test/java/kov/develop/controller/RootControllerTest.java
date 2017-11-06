package kov.develop.controller;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RootControllerTest extends AbstractControllerTest {

    @Test
    public void testRoot() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("meetings"))
                .andExpect(forwardedUrl("/WEB-INF/pages/meetings.jsp"))
                .andExpect(model().attributeExists("employers", "departs"));
    }

    @Test
    public void testForm() throws Exception {
        mockMvc.perform(get("/meeting/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("meetingForm"))
                .andExpect(forwardedUrl("/WEB-INF/pages/meetingForm.jsp"))
                .andExpect(model().attributeExists("allEmployers", "departs", "meeting", "currentDepartId", "membersOfMeeting"));
    }
}
