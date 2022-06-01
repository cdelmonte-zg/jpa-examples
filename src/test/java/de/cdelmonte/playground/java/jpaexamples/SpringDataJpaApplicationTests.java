package de.cdelmonte.playground.java.jpaexamples;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.cdelmonte.playground.java.jpaexamples.user.domain.User;
import de.cdelmonte.playground.java.jpaexamples.user.domain.UserRepository;

@SpringBootTest

/**
 * Using the @TestInstance(TestInstance.Lifecycle.PER_CLASS) annotation, we ask
 * JUnit 5 to create one single instance of the test class and reuse it for all
 * test methods. This will allow to make the @BeforeAll and @AfterAll annotated
 * methods non-static and to directly use inside them the auto-wired
 * UserRepository instance field.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class SpringDataJpaApplicationTests {
    @Autowired
    protected UserRepository userRepository;

    @BeforeAll
    public void beforeAll() {
        userRepository.saveAll(generateData());
    }

    private static Set<User> generateData() {
        Set<User> users = new HashSet<>();

        User bob = new User("bob", LocalDate.of(2018, Month.APRIL, 13));
        bob.setEmail("bob@somedomain.com");
        bob.setLevel(1);
        bob.setActive(true);
        users.add(bob);

        User alice = new User("alice", LocalDate.of(2020, Month.APRIL, 13));
        alice.setEmail("alice@somedomain.com");
        alice.setLevel(1);
        alice.setActive(true);
        users.add(alice);

        return users;
    }

    @AfterAll
    public void afterAll() {
        userRepository.deleteAll();
    }
}
