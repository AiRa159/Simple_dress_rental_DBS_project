package com.example.project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "review")
@Getter
@Setter
public class Review extends AbstractClass{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author", nullable = false)
    private Client client;

    @Column(name = "rev_comment")
    private String comment;

    @Column(name = "stars")
    private Integer stars;

    @Override
    public String toString(){
        return "Review: \n" +
                "id: " + getId() +
                "\nclient: " + this.client +
                "\nstars: " + this.stars +
                "\ncomment: " + this.comment;
    }
}
