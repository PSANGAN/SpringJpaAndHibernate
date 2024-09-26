package com.jpa.mssql.poc.demoJpawithMSSql.repository;

import com.jpa.mssql.poc.demoJpawithMSSql.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByOrderByUsernameAsc();
    List<User> findByRegistrationDateBetween(LocalDate start, LocalDate end);
    List<User> findByUsernameAndEmail(String username, String email);
    List<User> findByUsernameOrEmail(String username, String email);
    List<User> findByUsernameIgnoreCase(String username);
    List<User> findByLevelOrderByUsernameDesc(int level);
    List<User> findByLevelGreaterThanEqual(int level);
    List<User> findByUsernameContaining(String text);
    List<User> findByUsernameLike(String text);
    List<User> findByUsernameStartingWith(String start);
    List<User> findByUsernameEndingWith(String end);
    List<User> findByActive(boolean active);
    List<User> findByRegistrationDateIn(Collection<LocalDate> dates);
    List<User> findByRegistrationDateNotIn(Collection<LocalDate> dates);

    User findFirstByOrderByUsernameAsc();
    User findTopByOrderByRegistrationDateDesc();
    Page<User> findAll(Pageable pageable);
    List<User> findFirst2ByLevel(int level, Sort sort);
    List<User> findByLevel(int level, Sort sort);
    List<User> findByActive(boolean active, Pageable pageable);

    Streamable<User> findByEmailContaining(String text);
    Streamable<User> findByLevel(int level);
}
