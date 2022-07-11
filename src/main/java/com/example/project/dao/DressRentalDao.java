package com.example.project.dao;

import com.example.project.model.DressRental;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class DressRentalDao extends BaseDao{
    protected DressRentalDao() {
        super(DressRental.class);
    }

    public List<DressRental> findAllDressRentalsWithName(String name) {
        try {
            return em.createNamedQuery("DressRental.findByName", DressRental.class)
                    .setParameter("name", name)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
