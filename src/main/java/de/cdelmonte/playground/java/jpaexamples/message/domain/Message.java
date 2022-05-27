package de.cdelmonte.playground.java.jpaexamples.message.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString

@Entity
@Table(name="messages")
public class Message {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="payload", nullable=false)
    private String payload;

}
