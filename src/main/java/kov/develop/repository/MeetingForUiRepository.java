package kov.develop.repository;

import kov.develop.model.MeetingForUi;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MeetingForUiRepository {

    @PersistenceContext
    private EntityManager em;


    public List<MeetingForUi> getAll() {
        return em.createNamedQuery(MeetingForUi.GET_ALL, MeetingForUi.class).getResultList();
    }

}
