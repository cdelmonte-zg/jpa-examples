package de.cdelmonte.playground.java.jpaexamples.configuration;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.cdelmonte.playground.java.jpaexamples.user.domain.User;
import de.cdelmonte.playground.java.jpaexamples.user.domain.UserRepository;

@Configuration
public class StaticDataLoader {

    @Bean
    public ApplicationRunner loadStaticDataForUser(UserRepository repository) {

        return env -> {
            User michael = new User("Micki", LocalDate.now());
            User lisbeth = new User("liz94", LocalDate.of(2020, Month.DECEMBER, 24));

            repository.save(michael);
            repository.save(lisbeth);

            repository.findAll().forEach(System.out::println);
        };
    }
}
