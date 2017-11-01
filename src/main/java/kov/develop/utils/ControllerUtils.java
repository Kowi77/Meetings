package kov.develop.utils;

import kov.develop.model.MeetingForUi;
import kov.develop.model.Meeting;
import kov.develop.service.DepartService;
import kov.develop.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.StringJoiner;

public class ControllerUtils {

    @Autowired
    EmployerService employerService;
    @Autowired
    DepartService departService;

    //Prepare error message for response
    public static ResponseEntity<String> getErrors(BindingResult result){
        StringJoiner joiner = new StringJoiner("<br>");
        result.getFieldErrors().forEach(
                fe -> {
                    String msg = fe.getDefaultMessage();
                    if (!msg.startsWith(fe.getField())) {
                        msg = fe.getField() + ' ' + msg;
                    }
                    joiner.add(msg);
                });
        return new ResponseEntity<>(joiner.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public List<MeetingForUi> getMeetingForUi (List<Meeting> meetings){
       /* List<MeetingForUi> result = new ArrayList<>();
        for (Meeting meeting : meetings){
            Employer employer = employerService.get(meeting.getEmployerId());
            String[] name = employer.getFullname().split(" ");
            String shortName = name[0].concat(" ").concat(name[1].substring(0)).concat(" ").concat(name[2].substring(0));
            result.add(new MeetingForUi(meeting.getId(), meeting.getDate(), meeting.getTheme(), departService.get(employer.getDepartId()).getName(),
                  shortName,

        }*/
       return null;
    }
}
