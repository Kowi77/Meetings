package kov.develop.repository;

import kov.develop.model.Depart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface DepartRepository extends JpaRepository<Depart, Integer> {

    @Override
    Depart findOne(Integer id);

    @Override
    List<Depart> findAll();

}
