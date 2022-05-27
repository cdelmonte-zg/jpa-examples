package de.cdelmonte.playground.java.jpaexamples.message.domain;

import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long>{
    
}
