package com.example.project.dao;

import com.example.project.model.Dress;
import org.springframework.stereotype.Repository;

@Repository
public class DressDao extends BaseDao{
    protected DressDao() {
        super(Dress.class);
    }
}
