package kov.develop.repository;

import kov.develop.model.MeetingForUi;
import kov.develop.model.Meeting;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Integer> {

    @Override
    List<Meeting> findAll();

    @Override
    Meeting findOne(Integer id);

    @Override
    @Transactional
    Meeting save(Meeting meeting);

    List<Meeting> findAllByEmployerIdEquals(int id);

  /*  @Query (value = "SELECT new MeetingForUi(Meets.id, Meets.date, Meets.theme, Employers.fullName AS employer, Departs.name AS depart, COUNT(Members.employer_id) AS quantity) FROM Meets" +
            " LEFT JOIN Employers ON Meets.employer_id = Employers.id" +
            " LEFT JOIN Departs ON Employers.depart_id = Departs.id" +
            " LEFT JOIN Members ON Meets.id = Members.meet_id" +
            " GROUP BY Meets.theme", nativeQuery = true)
    List<MeetingForUi> getAllForUi();*/

   /* @Query (value = "SELECT m.id, m.date, m.theme, m.employerId FROM Meeting m")
    List<Meeting> findAllTest();*/

    /*    @Query (value = "SELECT Meeting.id, Meeting.date, Meeting.theme, Employer.fullName, Depart.name, COUNT(Members.employer_id) FROM Meeting" +
            " LEFT JOIN Employer ON Meeting.employer_id = Employer.id" +
            " LEFT JOIN Depart ON Employer.depart_id = Depart.id" +
            " LEFT JOIN Members ON Meeting.id = Members.meet_id" +
            " GROUP BY Meeting.theme")*/
    //List<MeetingForUi> getAllMeetingForUi();



    //TODO
    //Поиск по дате.времени
    //Удаление




}