package kov.develop.controller;


import kov.develop.model.MeetingForUi;
import kov.develop.model.Meeting;
import kov.develop.repository.MeetingForUiRepository;
import kov.develop.service.DepartService;
import kov.develop.service.EmployerService;
import kov.develop.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.TypedQuery;
import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Main rest controller
 * Works at "/meetings" for getAll (GET)
 *       at "/depart/{id}"  at "/employer/{id}" (GET) for filter by depart or emploryer
 *       at "/date" (POST) for filter by date
 */
@RestController
@RequestMapping(value = MainRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MainRestController {

    static final String REST_URL = "/meetings";
    //База не обрабатывает LocalDateTime.MAX и MIN. Установлены hardcode
    static final LocalDateTime MAX_DATE = LocalDateTime.parse("9999-12-31T23:59");
    static final LocalDateTime MIN_DATE = LocalDateTime.parse("0000-01-01T00:00");

    private DepartService departServiceservice;
    private MeetingService meetingService;
    private EmployerService employerService;

    @Autowired
    public MainRestController(DepartService departServiceservice, MeetingService meetingService, EmployerService employerService) {
        this.departServiceservice = departServiceservice;
        this.meetingService = meetingService;
        this.employerService = employerService;
    }

    @GetMapping
    public List<MeetingForUi> getAll() {
        return meetingService.getAllForUi();
    }

    @GetMapping("depart/{id}")
    public List<MeetingForUi> filterByDepart (@PathVariable("id") int id){

        return meetingService.getFilteredByDepart(id);
     }

    @GetMapping("employer/{id}")
    public List<MeetingForUi> filterByEmployer (@PathVariable("id") int id){
       return meetingService.getFilteredByEmployer(id);
    }

    @GetMapping("member/{id}")
    public List<MeetingForUi> filterByMember (@PathVariable("id") int id){
        return meetingService.getFilteredByMember(id);
    }

    @PostMapping(value = "/date")
    public List<MeetingForUi> filterByDate(
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate) {
        LocalDateTime start = (startDate.equals("") ? MIN_DATE: LocalDateTime.parse(startDate));
        LocalDateTime end = (endDate.equals("") ? MAX_DATE : LocalDateTime.parse(endDate));
        return meetingService.getFilteredByDate(start, end);
    }
}