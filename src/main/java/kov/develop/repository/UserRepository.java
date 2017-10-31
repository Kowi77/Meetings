package kov.develop.repository;


import kov.develop.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Use standart Spring Data methods
 */

@Repository
@Transactional(readOnly = true)
public abstract class UserRepository implements JpaRepository<Employer, Integer> {

  /*  @Override
    List<User> findAll();

    @Override
    User findOne(Integer id);

    @Override
    @Transactional
    void delete(Integer integer);

    @Override
    @Transactional
    User save(User user);*/

}
