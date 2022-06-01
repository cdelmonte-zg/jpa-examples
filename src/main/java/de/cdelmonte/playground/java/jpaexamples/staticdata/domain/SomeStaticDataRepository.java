package de.cdelmonte.playground.java.jpaexamples.staticdata.domain;

import org.springframework.data.repository.CrudRepository;

public interface SomeStaticDataRepository extends CrudRepository<SomeStaticData, Long>{
    
}
