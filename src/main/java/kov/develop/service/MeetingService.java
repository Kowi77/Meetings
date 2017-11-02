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

    public MeetingService(MeetingRepository meetingRepository, MeetingForUiRepository meetingForUiRepository, EmployerRepository employerRepository) {
        this.meetingRepository = meetingRepository;
        this.meetingForUiRepository = meetingForUiRepository;
        this.employerRepository = employerRepository;
    }

    @Autowired


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

    public List<Meeting> filterByDepart(int id){
        Set<Integer> set = employerRepository.findByDepartIdEquals(id).stream().map(e -> e.getId()).collect(Collectors.toSet());
        //TODO исправить, добавить поле depart_id в meeting
        List<Meeting> list = new ArrayList<>();
        set.forEach(e -> list.addAll(meetingRepository.findAllByEmployerIdEquals(e)));
        return list;
    }
    //Возвращаем только по собраниям, где работник ДОКЛАДЧИК, не участник
    public List<Meeting> filterByEmployer(int id){
        return meetingRepository.findAllByEmployerIdEquals(id);
    }

}
