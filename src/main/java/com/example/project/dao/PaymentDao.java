package com.example.project.dao;

import com.example.project.model.Payment;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentDao extends BaseDao{
    protected PaymentDao() {
        super(Payment.class);
    }
}
