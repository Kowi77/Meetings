package kov.develop.controller;

import kov.develop.model.Meeting;
import kov.develop.repository.DepartRepository;
import kov.develop.service.DepartService;
import kov.develop.service.EmployerService;
import kov.develop.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/")
    public String root(Model model) {
        model.addAttribute("employers", employerService.getAll());
        model.addAttribute("departs", departService.getAll());
        return "meetings";
    }

    @GetMapping("/meeting/{id}")
    public String form(@PathVariable ("id") int id, Model model){
        Meeting meeting = (id == 0 ? new Meeting() : meetingService.get(id));
            model.addAttribute("meeting", meeting);
        return "meetingForm";
    }


}
