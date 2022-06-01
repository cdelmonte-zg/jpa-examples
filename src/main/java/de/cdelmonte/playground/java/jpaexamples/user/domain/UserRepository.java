package de.cdelmonte.playground.java.jpaexamples.user.domain;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String string);

    Set<User> findAllByOrderByUsernameAsc();

    List<User> findByRegistrationDateBetween(LocalDate of, LocalDate of2);

    List<User> findByUsernameAndEmail(String username, String email);

    List<User> findByUsernameOrEmail(String username, String email);

    List<User> findByUsernameIgnoreCase(String username);

    List<User> findByLevelOrderByUsernameDesc(int level);

    List<User> findByLevelGreaterThanEqual(int level);


    // where e.username like %?1%
    List<User> findByUsernameContaining(String text);

    // where e.username like ?1
    List<User> findByUsernameLike(String text);


    // where e.username like ?1%
    List<User> findByUsernameStartingWith(String start);


    // where e.username like %?1
    List<User> findByUsernameEndingWith(String end);

    List<User> findByActive(boolean active);

    List<User> findByRegistrationDateIn(Collection<LocalDate> dates);

    List<User> findByRegistrationDateNotIn(Collection<LocalDate> dates);
}
