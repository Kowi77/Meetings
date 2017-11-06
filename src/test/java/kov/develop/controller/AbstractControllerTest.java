package kov.develop.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kov.develop.config.AppConfig;
import kov.develop.config.DataConfig;
import kov.develop.config.InitConfig;
import kov.develop.config.WebConfig;
import kov.develop.model.EmployerForUi;
import kov.develop.model.MeetingForUi;
import kov.develop.service.DepartService;
import kov.develop.service.EmployerService;
import kov.develop.service.MeetingService;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {InitConfig.class, AppConfig.class, WebConfig.class, DataConfig.class})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public abstract class AbstractControllerTest {

    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter();

    static {
        CHARACTER_ENCODING_FILTER.setEncoding("UTF-8");
        CHARACTER_ENCODING_FILTER.setForceEncoding(true);
    }

    protected MockMvc mockMvc;
    protected final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Autowired
    protected EmployerService employerService;
    @Autowired
    protected MeetingService meetingService;
    @Autowired
    protected DepartService departService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(CHARACTER_ENCODING_FILTER)
                .build();
    }
    //Util methods
    protected List<MeetingForUi> checkAndGetMeetings(ResultActions resultActions) throws Exception{
        MvcResult result = resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        String resultAsString = result.getResponse().getContentAsString();
        List<MeetingForUi> list = mapper.readValue(resultAsString, new TypeReference<List<MeetingForUi>>(){});
        return list.stream().sorted((a, b) -> a.getId().compareTo(b.getId())).collect(Collectors.toList());
    }

    protected List<EmployerForUi> checkAndGetEmployers(ResultActions resultActions) throws Exception{
        MvcResult result = resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
        String resultAsString = result.getResponse().getContentAsString();
        List<EmployerForUi> list = mapper.readValue(resultAsString, new TypeReference<List<EmployerForUi>>(){});
        return list.stream().sorted((a, b) -> a.getId().compareTo(b.getId())).collect(Collectors.toList());
    }
}
