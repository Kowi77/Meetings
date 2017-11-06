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
}
