package kov.develop.controller;

import kov.develop.model.Employer;
import kov.develop.model.EmployerForUi;
import kov.develop.model.Meeting;
import kov.develop.model.MeetingForUi;
import kov.develop.service.DepartService;
import kov.develop.service.EmployerService;
import kov.develop.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Form rest controller
 * Works at "/meetings" for getAll (GET)
 *       at "/depart/{id}"  at "/employer/{id}" for filter by
 *       at "/meeting" for save(create) meeting (POST)
 */
@RestController
@RequestMapping(value = FormController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class FormController {

    static final String REST_URL = "/meeting";

    private DepartService departServiceservice;
    private MeetingService meetingService;
    private EmployerService employerService;

    @Autowired
    public FormController(DepartService departServiceservice, MeetingService meetingService, EmployerService employerService) {
        this.departServiceservice = departServiceservice;
        this.meetingService = meetingService;
        this.employerService = employerService;
    }

    @GetMapping("/employersByDepart/{departId}")
    public List<Employer> getAllByDepart(@PathVariable("departId") int departId) {
        return employerService.getAllByDepart(departId);
    }

    @GetMapping("/membersByMeet/{meetId}")
    public List<EmployerForUi> getAllMembersByMeet (@PathVariable("meetId") int meetId) {
        return employerService.getAllMembersOfMeeting (meetId);
    }

    @GetMapping("/employerForUiById/{empId}")
    public EmployerForUi get(@PathVariable("empId") int empId) {
        return employerService.get(empId);
    }

    @PostMapping
    public void saveMeeting (
        @RequestParam(value = "id", required = false) String id,
        @RequestParam(value = "theme") String theme,
        @RequestParam(value = "date") String dateTime,
        @RequestParam(value = "depart") String depart,
        @RequestParam(value = "selectDepart") String selectDepart,
        @RequestParam(value = "employer") String employer,
        @RequestParam(value = "selectEmployer") String selectEmployer,
        @RequestParam(value = "mems[]") Set<Integer> members)
    {
       Integer parseId = (id == "" ? null : Integer.parseInt(id));
       meetingService.save(new Meeting(parseId, theme, LocalDateTime.parse(dateTime), Integer.parseInt(employer), members));
    }
}
