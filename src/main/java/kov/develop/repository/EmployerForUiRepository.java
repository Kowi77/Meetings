package kov.develop.repository;

import kov.develop.model.Employer;
import kov.develop.model.EmployerForUi;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EmployerForUiRepository {

    @PersistenceContext
    private EntityManager em;

    public List<EmployerForUi> getAllMembersOfMeeting (int meetId) {
        return em.createNamedQuery(EmployerForUi.GET_ALL_MEMBERS, EmployerForUi.class).
                setParameter("meetId", meetId).getResultList();
    }
}
