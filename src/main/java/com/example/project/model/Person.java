package com.example.project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "person")
@Getter
@Setter
public abstract class Person{

    @Id
    @Column(name = "personal_id", nullable = false)
    private Integer personal_id;

    @Column(name = "first_n", nullable = false)
    private String first_n;

    @Column(name = "last_n", nullable = false)
    private String last_n;

    @Override
    public String toString(){
        return getClass().getSimpleName() + ":\n" +
                "id: " + this.personal_id +
                "\nname: " + this.first_n +
                "\nsurname: " + this.last_n;
    }

//    phone numbers
}