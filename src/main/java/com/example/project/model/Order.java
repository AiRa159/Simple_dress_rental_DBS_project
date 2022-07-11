package com.example.project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "client_order")
@Getter
@Setter
public class Order extends AbstractClass{

    @Column(name = "date_from", nullable = false)
    private Date dateFrom;

    @Column(name = "date_to", nullable = false)
    private Date dateTo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client", nullable = false)
    private Client client;

    @OneToMany(cascade = CascadeType.ALL)
//    @Column(name = "dress", nullable = false)
    private List<Dress> dressList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dr_id", nullable = false)
    private DressRental dressRental;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment", nullable = false)
    private Payment payment;

    @Override
    public String toString(){
        return "Order: \n" +
                "id: " + getId() +
                "\nclient: " + this.client +
                "\ndress rental: " + this.dressRental +
                "\nperiod: " + this.dateFrom + " - " + this.dateTo +
                printDresses();
    }

    public String printDresses(){
        for(Dress d : dressList){
            return "Dress serial number: " + d.getId() + "\n";
        }
        return " ";
    }

    public void addDress(Dress dress){
        dressList.add(dress);
    }

    public void removeDress(Dress dress){
            dressList.remove(dress);
    }

}
