package de.cdelmonte.playground.java.jpaexamples.staticdata.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
@Entity
@Table(name = "static_data")
public class SomeStaticData {

    public SomeStaticData(String data) {
        this.data = data;
    }

    public SomeStaticData() {}

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String data;
}
