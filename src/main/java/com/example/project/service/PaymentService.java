package com.example.project.service;

import com.example.project.dao.BaseDao;
import com.example.project.dao.PaymentDao;
import com.example.project.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService extends BaseService<Payment>{

    private final PaymentDao dao;

    @Autowired
    public PaymentService(PaymentDao dao) { this.dao = dao; }

    @Override
    protected BaseDao<Payment> getDao() { return dao; }

    @Transactional
    public void createNewPayment(Payment payment){
        if(payment == null){
            System.err.println("Payment is null");
            return;
        }

        if(payment.getPaymentDate() == null || payment.getAmount() == null){
            System.err.println("Wrong data!");
            return;
        }

        persist(payment);
    }

    @Transactional
    public void deletePayment(int id){
        Payment p = find(id);
        if(p == null){
            System.err.println("Payment with id " + id + " has not been found!");
            return;
        }
        delete(p);
    }
}
