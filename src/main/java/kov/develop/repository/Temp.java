package kov.develop.repository;

import kov.develop.model.Meeting;
import kov.develop.model.MeetingForUi;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class Temp {

    @PersistenceContext
    private EntityManager em;


    public TypedQuery<MeetingForUi> getBetween() {
        System.out.println("***************");
        TypedQuery<MeetingForUi> qqq = em.createNamedQuery(Meeting.TEST, MeetingForUi.class);
        System.out.println(qqq.toString());
        return qqq;

    }
}
