package kov.develop.service;

import kov.develop.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger log = LogManager.getLogger(UserService.class);

 /*   private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll (){
        log.info("Get all users");
        return repository.findAll();
    }

    public User get (int id){
        log.info("Get user with {} id ", id);
        return repository.findOne(id);
    }

    public void delete (int id){
        log.info("User with {} id deleted", id);
        repository.delete(id);
    }

    public User save (User user){
        if (user.getId() == null)
            log.info("Create new user");
        else log.info("User with {} id saved", user.getId());
        return repository.save(user);
    }*/
}
