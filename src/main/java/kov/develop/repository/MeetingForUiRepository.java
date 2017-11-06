package kov.develop.repository;

import kov.develop.model.Employer;
import kov.develop.model.MeetingForUi;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class MeetingForUiRepository {

    @PersistenceContext
    private EntityManager em;


    public List<MeetingForUi> getAll() {
        return em.createNamedQuery(MeetingForUi.GET_ALL, MeetingForUi.class).getResultList();
    }

    public List<MeetingForUi> getFilteredByDepart(int departId) {
        return em.createNamedQuery(MeetingForUi.GET_FILTERED_BY_DEPART, MeetingForUi.class).
                setParameter("departId", departId).getResultList();
    }

    public List<MeetingForUi> getFilteredByEmployer(int empId) {
        return em.createNamedQuery(MeetingForUi.GET_FILTERED_BY_EMPLOYER, MeetingForUi.class).
                setParameter("empId", empId).getResultList();
    }

    public List<MeetingForUi> getFilteredByDate (LocalDateTime start, LocalDateTime end) {
        return em.createNamedQuery(MeetingForUi.GET_FILTERED_BY_DATE, MeetingForUi.class).
                setParameter("start", start).setParameter("end", end).getResultList();
    }

}
