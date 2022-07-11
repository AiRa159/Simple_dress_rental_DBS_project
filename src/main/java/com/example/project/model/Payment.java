package com.example.project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "payment")
@Getter
@Setter
public class Payment extends AbstractClass{

    @Column(name = "p_date", nullable = false)
    private Date paymentDate;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Override
    public String toString(){
        return "Payment: \n" +
                "id: " + getId() +
                "\ndate: " + this.paymentDate +
                "\namount: " + this.amount;
    }
}
