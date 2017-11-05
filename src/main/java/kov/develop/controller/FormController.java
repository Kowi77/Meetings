package kov.develop.controller;

import kov.develop.model.Employer;
import kov.develop.model.Meeting;
import kov.develop.model.MeetingForUi;
import kov.develop.service.DepartService;
import kov.develop.service.EmployerService;
import kov.develop.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Form rest controller
 * Works at "/meetings" for getAll (GET)
 *       at "/depart/{id}"  at "/employer/{id}" for filter by
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

    @DeleteMapping("/employer/{id}")
    public void deleteEmployerFromMeeting (@PathVariable("id") int id){
        System.out.println("DELETE!!!!");
    }

    @PostMapping
    public void saveMeeting (
        @RequestParam(value = "id") int id,
        @RequestParam(value = "theme") String theme,
        @RequestParam(value = "date") String dateTime,
        @RequestParam(value = "depart") String depart,
        @RequestParam(value = "selectDepart") String selectDepart,
        @RequestParam(value = "member") String member,
        @RequestParam(value = "employer") String employer)
    {
        System.out.println(theme + dateTime + depart + selectDepart + member + employer);
    }
}
