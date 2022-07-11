package com.example.project.dao;

import com.example.project.model.Review;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewDao extends BaseDao{
    protected ReviewDao() {
        super(Review.class);
    }
}
