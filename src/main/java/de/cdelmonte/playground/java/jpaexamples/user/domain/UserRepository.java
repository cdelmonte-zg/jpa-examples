package de.cdelmonte.playground.java.jpaexamples.user.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String string);

    Set<User> findAllByOrderByUsernameAsc();

    List<User> findByRegistrationDateBetween(LocalDate of, LocalDate of2);
}
