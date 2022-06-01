package de.cdelmonte.playground.java.jpaexamples.user.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.data.util.Streamable;

import de.cdelmonte.playground.java.jpaexamples.SpringDataJpaApplicationTests;

public class UserTest extends SpringDataJpaApplicationTests{
    @Test
    void testFindAll() {
        Set<User> users =  Streamable.of(userRepository.findAll()).toSet();
        assertEquals(2, users.size());
    }

    @Test
    void testFindUser() {
        User beth = userRepository.findByUsername("bob");
        assertEquals("bob", beth.getUsername());
    }

    @Test
    void testFindAllByOrderByUsernameAsc() {
        List<User> users =  List.copyOf(userRepository.findAllByOrderByUsernameAsc());
        assertAll(() -> assertEquals(2, users.size()),
                () -> assertEquals("alice", users.get(0).getUsername()),
                () -> assertEquals("bob", 
                       users.get(users.size() - 1).getUsername()));
    }

    @Test
    void testFindByRegistrationDateBetween() {
        List<User> users = userRepository.findByRegistrationDateBetween(
                LocalDate.of(2018, Month.JULY, 1),
                LocalDate.of(2021, Month.DECEMBER, 31));
        assertEquals(1, users.size());
    } 
}
