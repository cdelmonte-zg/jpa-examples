package de.cdelmonte.playground.java.jpaexamples.picture.domain;

import java.io.ByteArrayInputStream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table
public class Picture {
    
    public Picture() {}

    @Id
    @GeneratedValue
    Long id;

    @Lob
    private java.sql.Blob imageBlob;
    
    @Lob
    private java.sql.Clob description;


}
