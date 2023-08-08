package edu.javacourse.city.domain;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
