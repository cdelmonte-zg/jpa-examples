package de.cdelmonte.playground.java.jpaexamples.configuration;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.cdelmonte.playground.java.jpaexamples.staticdata.domain.SomeStaticData;
import de.cdelmonte.playground.java.jpaexamples.staticdata.domain.SomeStaticDataRepository;

@Configuration
public class StaticDataLoader {

    @Bean
    public ApplicationRunner loadStaticDataForUser(SomeStaticDataRepository repository) {

        return env -> {
            SomeStaticData data1 = new SomeStaticData("DATA001");
            SomeStaticData data2 = new SomeStaticData("DATA002");
            
            repository.save(data1);
            repository.save(data2);

            repository.findAll().forEach(System.out::println);
        };
    }
}
