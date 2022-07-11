package com.example.project.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Objects;

public abstract class BaseDao<T> {

    @PersistenceContext
    protected EntityManager em;

    protected final Class<T> type;

    protected BaseDao(Class<T> type) {this.type = type;}

    public T findById(Integer id){
        Objects.requireNonNull(id);
        return em.find(type, id);
    }

    public List<T> findAll(){
        try{
            return em.createQuery("SELECT e FROM " + type.getSimpleName() + " e", type).getResultList();
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    public void persist(T entity){
        Objects.requireNonNull(entity);
        try {
            em.persist(entity);
        }catch (RuntimeException e){
            throw new PersistenceException(e);
        }
    }

    public T update(T entity) {
        Objects.requireNonNull(entity);
        try {
            return em.merge(entity);
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public void delete(T entity) {
        Objects.requireNonNull(entity);
        try {
            final T toRemove = em.merge(entity);
            if (toRemove != null) {
                em.remove(toRemove);
            }
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }


}
