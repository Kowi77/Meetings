package kov.develop.controller;

import kov.develop.model.Depart;
import kov.develop.model.Employer;
import kov.develop.model.Meeting;
import kov.develop.service.DepartService;
import kov.develop.service.EmployerService;
import kov.develop.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Welcome page
 */

@Controller
public class RootController {

    private MeetingService meetingService;
    private DepartService departService;
    private EmployerService employerService;

    @Autowired
    public RootController(MeetingService meetingService, DepartService departService, EmployerService employerService) {
        this.meetingService = meetingService;
        this.departService = departService;
        this.employerService = employerService;
    }

    //Базовая страница приложения
    @GetMapping("/")
    public String root(Model model) {
        model.addAttribute("employers", employerService.getAll());
        model.addAttribute("departs", departService.getAll());
        return "meetings";
    }

    //Переход на форму редактирования
    @GetMapping("/meeting/{id}")
    public String form(@PathVariable ("id") int id, ModelMap model){
        Meeting meeting = (id == 0 ? new Meeting() : meetingService.get(id));
        List<Depart> departs = departService.getAll();
        model.addAttribute("departs", departs);
        List<Employer> employers = employerService.getAll();
        model.addAttribute("allEmployers", employers);
        model.addAttribute("meeting", meeting);
        Employer emp = (Employer) employers.stream().filter(e -> e.getId() == meeting.getEmployerId()).collect(Collectors.toList()).get(0);
        model.addAttribute("currentDepartId", departs.stream().filter(d -> d.getId() == emp.getDepartId()).collect(Collectors.toList()).get(0).getId());
        model.addAttribute("employersOfMeeting", employerService.getAllMembersOfMeeting(id));
        return "meetingForm";
    }


}
