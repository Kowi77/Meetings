package kov.develop.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public interface MembersService {

    @Query("SELECT m.employer_id FROM members m WHERE m.meet_id=:meetId")
    Set<Integer> getMembersId(@Param("meetId") int meetId);
}
