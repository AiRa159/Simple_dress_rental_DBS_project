package com.example.project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "client")
@Getter
@Setter
public class Client extends Person{

    @Column
    private Integer size;

    @ElementCollection
    @Column(unique = true)
    private Collection<String> emails;

    public void addEmail(String email){
        if(!emails.contains(email)){
            emails.add(email);
        }
    }

    public void removeEmail(String email){
        if(emails.contains(email)){
            emails.remove(email);
        }
    }
}
