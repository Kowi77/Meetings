package kov.develop.repository;

import kov.develop.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    //TODO
    //Поиск по дате.времени
    //Поиск по докладчику
    //Удаление




}