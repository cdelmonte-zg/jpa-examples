package de.cdelmonte.playground.java.jpaexamples.user.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnTransformer;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The persistence-capable class and any of its methods shouldn’t be final (a
 * requirement of the JPA specification). Hibernate however allow declaring
 * final classes as entities or entities with final methods
 * that access persistent fields.
 * 
 * However, this is not a good practice, as this
 * will prevent Hibernate from using the proxy pattern for performance
 * improvement.
 */

@Getter
@Setter
@ToString
@Entity
@Table(name = "USERS")
public class User {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue
    private Long id;

    /**
     * We remind that JPA requires a constructor with no arguments for every
     * persistent class. JPA uses the Java reflection API on such a no-argument
     * constructor to create instances.
     */
    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, LocalDate registrationDate) {
        this.username = username;
        this.registrationDate = registrationDate;
    }

    @Column(name = "username")
    private String username;

    private String email;
 
    private int level;
 
    private boolean active;

    @Column(name ="registration_date")
    private LocalDate registrationDate;

    /**
     * The persistence-capable class and any of its methods shouldn’t be final (a
     * requirement of the JPA specification). Hibernate is not so strict, and it
     * will allow declaring final classes as entities or entities with final methods
     * that access persistent fields. However, this is not a good practice, as this
     * will prevent Hibernate from using the proxy pattern for performance
     * improvement.
     */

}
