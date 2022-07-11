package com.example.project.service;

import com.example.project.dao.BaseDao;
import com.example.project.dao.DressDao;
import com.example.project.dao.OrderDao;
import com.example.project.model.Client;
import com.example.project.model.Dress;
import com.example.project.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Service
public class OrderService extends BaseService<Order>{
    private final OrderDao dao;
    private final DressDao dressDao;

    @Autowired
    public OrderService(OrderDao dao, DressDao dressDao) {
        this.dao = dao;
        this.dressDao = dressDao;
    }

    @Override
    protected BaseDao<Order> getDao() {
        return this.dao;
    }

    @Transactional
    public void createOrder(Order order){
        if(order == null){
            System.err.println("Order id null");
            return;
        }
        if(order.getClient() == null || order.getDateFrom() == null || order.getDateTo() == null
        || order.getDressList().isEmpty() || order.getDressRental() == null || order.getPayment() == null){
            System.err.println("Wrong data");
            return;
        }
        for (Dress d : order.getDressList()){
            if(!isAvailable(d.getId(), order.getDateFrom())){
                System.err.println("Dress with id " + d.getId() + " is not available!");
                return;
            }
        }

        persist(order);
    }

    @Transactional
    public void deleteOrder(int id){
        Order o = find(id);
        if(o == null){
            System.err.println("Order with id " + id + " has not been found!");
            return;
        }
        delete(o);
    }

    @Transactional
    public void setClient(int id, Client client){
        Order o = find(id);
        if (o == null) {
            System.err.println("Order with id " + id + " has not been found!");
            return;
        }
        if(client == null){
            System.err.println("Client is null");
            return;
        }
        o.setClient(client);
        dao.update(o);
    }

    @Transactional
    public void addDress(int id, Dress dress){
        Order o = find(id);
        if (o == null) {
            System.err.println("Order with id " + id + " has not been found!");
            return;
        }
        if(dress == null){
            System.err.println("Dress is null");
            return;
        }
        if(o.getDressList().contains(dress)){
            System.err.println("This dress has already been added");
            return;
        }
        if(!isAvailable(dress.getId(), o.getDateFrom())){
            System.err.println("Dress with id " + dress.getId() + " is not available!");
            return;
        }
        o.addDress(dress);
        update(o);
    }

    @Transactional
    public void deleteDress(int id, int dress_id){
        Order o = find(id);
        Dress d = (Dress) dressDao.findById(dress_id);
        if (o == null) {
            System.err.println("Order with id " + id + " has not been found!");
            return;
        }
        if(d == null){
            System.err.println("Dress with id " + id + " has not been found!");
            return;
        }
        if(!o.getDressList().contains(d)){
            System.err.println("The order does not contain this dress");
            return;
        }
        o.removeDress(d);
        update(o);
    }

    public boolean isAvailable(int dress_id, Date from){
        List<Order> orders = dao.getAllOrdersWithDress(dress_id);
        for(Order o : orders){
            if(o.getDateFrom() == from){
                return false;
            }else if(from.after(o.getDateFrom()) && from.before(o.getDateTo())){
                return false;
            }
        }
        return true;
    }
}
