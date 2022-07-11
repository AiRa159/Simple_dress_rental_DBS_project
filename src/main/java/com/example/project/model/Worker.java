package com.example.project.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "worker")
@Getter
@Setter
public class Worker extends Person{

    @ManyToMany(cascade = CascadeType.ALL)
    public List<Worker> colleagues;
}
