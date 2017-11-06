package kov.develop.service;

import kov.develop.model.Meeting;
import kov.develop.model.MeetingForUi;
import kov.develop.repository.EmployerRepository;
import kov.develop.repository.MeetingForUiRepository;
import kov.develop.repository.MeetingRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MeetingService {

    private static final Logger log = LogManager.getLogger(MeetingService.class);

    private MeetingRepository meetingRepository;
    private MeetingForUiRepository meetingForUiRepository;
    private EmployerRepository employerRepository;

    @Autowired
    public MeetingService(MeetingRepository meetingRepository, MeetingForUiRepository meetingForUiRepository, EmployerRepository employerRepository) {
        this.meetingRepository = meetingRepository;
        this.meetingForUiRepository = meetingForUiRepository;
        this.employerRepository = employerRepository;
    }

    public List<Meeting> getAll (){
        log.info("Get all Meetings");
        return meetingRepository.findAll();
    }

    public List<MeetingForUi> getAllForUi (){
        return meetingForUiRepository.getAll();
    }

    public Meeting get (int id){
        log.info("Get Meeting with {} id ", id);
        return meetingRepository.findOne(id);
    }

    public void delete (int id){
        log.info("Meeting with {} id deleted", id);
        meetingRepository.delete(id);
    }

    public Meeting save (Meeting meeting){
        if (meeting.getId() == null)
            log.info("Create new Meeting");
        else log.info("Meeting with {} id saved", meeting.getId());
        return meetingRepository.save(meeting);
    }

    public List<MeetingForUi> getFilteredByDepart(int id){
        log.info("Get Meetings by departament {} id ", id);
        return meetingForUiRepository.getFilteredByDepart(id);
    }
    //Возвращаем только по собраниям, где работник ДОКЛАДЧИК, не участник
    public List<MeetingForUi> getFilteredByEmployer(int id){
        log.info("Get Meetings by employer {} id ", id);
        return meetingForUiRepository.getFilteredByEmployer(id);
    }
    public List<MeetingForUi> getFilteredByDate(LocalDateTime start, LocalDateTime end){
        log.info("Get Meetings beetwen {} and {} ", start, end);
        return meetingForUiRepository.getFilteredByDate(start, end);
    }

}
