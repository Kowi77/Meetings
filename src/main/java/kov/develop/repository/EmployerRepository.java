package kov.develop.repository;

import kov.develop.model.Employer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Use standart Spring Data methods
 */

@Repository
@Transactional(readOnly = true)
public interface EmployerRepository extends JpaRepository<Employer, Integer> {

    @Override
    List<Employer> findAll();

    @Override
    Employer findOne(Integer id);

    Employer findByDepartIdEquals(Integer id);

}
