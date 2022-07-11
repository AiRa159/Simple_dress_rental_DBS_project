package com.example.project.dao;

import com.example.project.model.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class OrderDao extends BaseDao{
    protected OrderDao() {
        super(Order.class);
    }

    public List<Order> getAllOrdersWithDress(int id){
        try {
            return em.createQuery("SELECT o FROM Order o where :dress_id member of o.dressList")
                    .setParameter("dress_id", id)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
