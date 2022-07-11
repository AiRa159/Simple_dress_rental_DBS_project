package com.example.project.dao;

import com.example.project.model.Creator;
import com.example.project.model.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class PersonDao extends BaseDao{
    public PersonDao() {
        super(Person.class);
    }

    public List<Creator> getAllCreators() {
        try {
            return em.createNamedQuery("Creator.getAllData", Creator.class).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
