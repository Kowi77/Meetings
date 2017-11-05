package kov.develop.service;


import kov.develop.model.Employer;
import kov.develop.model.EmployerForUi;
import kov.develop.repository.EmployerForUiRepository;
import kov.develop.repository.EmployerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerService {

    private static final Logger log = LogManager.getLogger(EmployerService.class);

    private EmployerRepository repository;
    private EmployerForUiRepository forUiRepository;

    @Autowired
    public EmployerService(EmployerRepository repository, EmployerForUiRepository forUiRepository) {
        this.repository = repository;
        this.forUiRepository = forUiRepository;
    }

    public List<Employer> getAll (){
        log.info("Get all Employers");
        return repository.findAll();
    }

    public Employer get (int id){
        log.info("Get Employer with {} id ", id);
        return repository.findOne(id);
    }

    public List<Employer> getAllByDepart(int departId){
        log.info("Get all employers from depart with {} id ", departId);
        return repository.findByDepartIdEquals(departId);
    }

    public List<EmployerForUi> getAllMembersOfMeeting(int meetId){
        return forUiRepository.getAllMembersOfMeeting(meetId);
    }



}