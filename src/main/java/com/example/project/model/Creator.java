package com.example.project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@NamedQuery(name = "Creator.getAllData", query = "SELECT cr FROM Creator cr")
//@Table(name = "creator")
@Getter
@Setter
public class Creator extends Person{

    @ManyToMany
    private Collection<Dress> dresses;

    public Creator() {
        this.dresses = new ArrayList<>();
    }

    public void addDress(Dress dress){
        if(dresses.contains(dress)) {
            System.err.println("Dress is already added!");
        }
        dresses.add(dress);

    }

    public void removeDress(Dress dress){
        if(!dresses.contains(dress)) {
            System.err.println("The author does not have such a dress!");
        }
        dresses.remove(dress);
    }

    public void printArray(){
        for(Dress d : dresses){
            System.out.println(d.toString());
        }
    }
}
