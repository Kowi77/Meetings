package kov.develop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static kov.develop.utils.ControllerUtils.getErrors;

/**
 * Main rest controller
 * Works at "/user" for getAll (GET) and save/create (POST)
 *       at "/user/{id}" for delete(DELETE) and getOne (GET)
 */
@RestController
@RequestMapping(value = UserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {

    static final String REST_URL = "/meetings";

   /* private UserService service;

    @Autowired
    public UserRestController(UserService service) { this.service = service; }

    //Resolve some problems with auto parsing Date and empty id in new User
    @InitBinder
    protected void initBuilder(WebDataBinder binder){
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException{
                setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }

            @Override
            public String getAsText() throws IllegalArgumentException {
                return DateTimeFormatter.ofPattern("yyyy-MM-dd").format((LocalDate) getValue());
            }
        });
        binder.registerCustomEditor(Integer.class, new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException{
                try {
                    setValue(Integer.parseInt(text));
                } catch (NumberFormatException e) {
                    // = NEW USER! Don't need to handle this exception
                }
            }
        });
    }

    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") int id){
        return service.get(id);
    }

    @DeleteMapping("/{id}")
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