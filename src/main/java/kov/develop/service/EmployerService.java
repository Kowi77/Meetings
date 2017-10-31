package kov.develop.service;


import kov.develop.model.Employer;
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

    @Autowired
    public EmployerService(EmployerRepository repository) {
        this.repository = repository;
    }

    public List<Employer> getAll (){
        log.info("Get all Employers");
        return repository.findAll();
    }

    public Employer get (int id){
        log.info("Get Employer with {} id ", id);
        return repository.findOne(id);
    }



}