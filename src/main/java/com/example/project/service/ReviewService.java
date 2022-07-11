package com.example.project.service;

import com.example.project.dao.BaseDao;
import com.example.project.dao.ReviewDao;
import com.example.project.model.Payment;
import com.example.project.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService extends BaseService<Review>{

    private final ReviewDao dao;

    @Autowired
    public ReviewService(ReviewDao dao) { this.dao = dao; }

    @Override
    protected BaseDao<Review> getDao() { return dao; }

    @Transactional
    public void createNewReview(Review review){
        if(review == null){
            System.err.println("Review is null");
            return;
        }

        if(review.getClient() == null || (review.getStars() < 0 && review.getStars() > 5)){
            System.err.println("Wrong data!");
            return;
        }

        persist(review);
    }

    @Transactional
    public void deleteReview(int id){
        Review r = find(id);
        if(r == null){
            System.err.println("Review with id " + id + " has not been found!");
            return;
        }
        delete(r);
    }

    @Transactional
    public void setComment(int id, String text){
         Review r = find(id);
         if(r == null){
            System.err.println("Review with id " + id + " has not been found!");
            return;
         }
         r.setComment(text);
         update(r);
    }

    @Transactional
    public void setStars(int id, int stars){
        Review r = find(id);
        if(r == null){
            System.err.println("Review with id " + id + " has not been found!");
            return;
        }
        if(stars < 0 || stars > 5){
            System.err.println("The number of stars can be in the range from 0 to 5");
            return;
        }
        r.setStars(stars);
        update(r);
    }


}
