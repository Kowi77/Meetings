package kov.develop.controller;


import kov.develop.model.MeetingForUi;
import kov.develop.model.Meeting;
import kov.develop.repository.Temp;
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
 * Works at "/user" for getAll (GET) and save/create (POST)
 *       at "/user/{id}" for delete(DELETE) and getOne (GET)
 */
@RestController
@RequestMapping(value = MainRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MainRestController {

    static final String REST_URL = "/meetings";

    private DepartService departServiceservice;
    private MeetingService meetingService;
    private EmployerService employerService;
    @Autowired
    private Temp temp;

    @Autowired

    public MainRestController(DepartService departServiceservice, MeetingService meetingService, EmployerService employerService) {
        this.departServiceservice = departServiceservice;
        this.meetingService = meetingService;
        this.employerService = employerService;
    }

    @GetMapping
    public List<Meeting> getAll() {
        TypedQuery<MeetingForUi> query = temp.getBetween();
        System.out.println(query.getResultList().toString());
        return meetingService.getAll();
    }


    //Resolve some problems with auto parsing Date and empty id in new User
    @InitBinder
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
    }

    @GetMapping("depart/{id}")
    public List<Meeting> filterByDepart (@PathVariable("id") int id){

        return meetingService.filterByDepart(id);
    }

    @GetMapping("employer/{id}")
    public List<Meeting> filterByEmployer (@PathVariable("id") int id){
        return meetingService.filterByEmployer(id);
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