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


    //Resolve some problems with auto parsing Date and empty id in new User
   /* @InitBinder
    protected void initBuilder(WebDataBinder binder){
        binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException{
                setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            }

            @Override
            public String getAsText() throws IllegalArgumentException {
                return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format((LocalDateTime) getValue());
            }
        });
        binder.registerCustomEditor(Integer.class, new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException{
                try {
                    setValue(Integer.parseInt(text));
                } catch (NumberFormatException e) {
                    // = NEW Meeting! Don't need to handle this exception
                }
            }
        });
    }*/

    @GetMapping("depart/{id}")
    public List<MeetingForUi> filterByDepart (@PathVariable("id") int id){

        return meetingService.getFilteredByDepart(id);
     }

    @GetMapping("employer/{id}")
    public List<MeetingForUi> filterByEmployer (@PathVariable("id") int id){
       return meetingService.getFilteredByEmployer(id);
    }


    @PostMapping(value = "/date")
    public List<MeetingForUi> filterByDate(
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate) {
        LocalDateTime start = (startDate.equals("") ? MIN_DATE: LocalDateTime.parse(startDate));
        LocalDateTime end = (endDate.equals("") ? MAX_DATE : LocalDateTime.parse(endDate));
        return meetingService.getFilteredByDate(start, end);
    }



    /*@DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id){
        service.delete(id);
    }

    //Data from request recieved via Spring binding and checeked by validators
    // Return response with error's definition, if it's need
    @PostMapping
    public ResponseEntity<String> createOrUpdate(@Valid User user, BindingResult result) {
        ResponseEntity<String> response;
        if (result.hasErrors()){
            response = getErrors(result);
            return getErrors(result);}
      service.save(user);
      return new ResponseEntity<>(HttpStatus.OK);
    }*/

}