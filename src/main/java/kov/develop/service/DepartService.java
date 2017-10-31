package kov.develop.service;

import kov.develop.model.Depart;
import kov.develop.repository.DepartRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartService {

    private DepartRepository repository;

    @Autowired
    public DepartService(DepartRepository repository) {
        this.repository = repository;
    }

    private static final Logger log = LogManager.getLogger(DepartService.class);

    public List<Depart> getAll (){
        log.info("Get all Departs");
        return repository.findAll();
    }

    public Depart get (int id){
        log.info("Get Depart with {} id ", id);
        return repository.findOne(id);
    }
}
