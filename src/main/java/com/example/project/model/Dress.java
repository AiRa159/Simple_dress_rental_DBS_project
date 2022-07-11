package com.example.project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "dress")
@Getter
@Setter
public class Dress extends AbstractClass{

    @Column(name = "dress_size", nullable = false)
    private Integer dress_size;

    @Column(name = "dress_style", nullable = false)
    private String dress_style;

    @ManyToMany(mappedBy = "dresses")
    private Collection<Creator> creatorList;

    public Dress() {
        creatorList = new ArrayList<>();
    }

    @Override
    public String toString(){
        return "Dress: \n" +
                "serial number: " + getId() +
                "\nsize: " + this.dress_size +
                "\nstyle: " + this.dress_style;
    }

    public void printArray(){
        for(Creator c : creatorList){
            System.out.println(c.toString());
        }
    }

}