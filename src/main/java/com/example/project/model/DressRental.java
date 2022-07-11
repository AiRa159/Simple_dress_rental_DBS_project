package com.example.project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@NamedQueries({
    @NamedQuery(name = "DressRental.findByName", query = "SELECT dr from DressRental dr where dr.name = :name")
})

@Entity
@Table(name = "dress_rental")
@Getter
@Setter
public class DressRental extends AbstractClass{

    @Column(name = "dr_name", nullable = false)
    private String name;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Override
    public String toString(){
        return "Dress rental: \n" +
                "id: " + getId() +
                "\nname: " + this.name +
                "\ncountry: " + this.country +
                "\ncity: " + this.city +
                "\nstreet: " + this.street;
    }
}
