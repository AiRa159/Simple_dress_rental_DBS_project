package com.example.project.service;

import com.example.project.dao.BaseDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

public abstract class BaseService<T> {

    protected abstract BaseDao<T> getDao();

    @Transactional
    public List<T> getAll() {
        return getDao().findAll();
    }

    @Transactional
    public T find(Integer id){ return getDao().findById(id); }

    @Transactional
    protected void persist(T instance) {
        Objects.requireNonNull(instance);
        getDao().persist(instance);
    }

    @Transactional
    protected void update(T instance) {
        Objects.requireNonNull(instance);
        getDao().update(instance);
    }

    @Transactional
    protected void delete(T instance) {
        Objects.requireNonNull(instance);
        getDao().delete(instance);
    }
}
