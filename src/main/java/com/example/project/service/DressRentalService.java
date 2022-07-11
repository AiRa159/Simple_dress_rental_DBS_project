package com.example.project.service;

import com.example.project.dao.BaseDao;
import com.example.project.dao.DressRentalDao;
import com.example.project.model.Dress;
import com.example.project.model.DressRental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DressRentalService extends BaseService<DressRental>{
    private final DressRentalDao dao;

    @Autowired
    public DressRentalService(DressRentalDao dao) {
        this.dao = dao;
    }

    @Override
    protected BaseDao<DressRental> getDao() {
        return this.dao;
    }

    public void createDressRental(String name, String country, String city, String street){
        System.out.println("Creating a dress rental...............");
        if(name.equals("") || country.equals("") || city.equals("") || street.equals("")){
            System.err.println("Wrong data!");
            return;
        }
        List<DressRental> list = getAllDressRentalsByName(name);
        DressRental dressRental = new DressRental();
        dressRental.setName(name);
        dressRental.setCountry(country);
        dressRental.setCity(city);
        dressRental.setStreet(street);
        if(list.isEmpty()){
            persist(dressRental);
        }else{
            for(DressRental d : list){
                if(d.getName() == name && d.getCountry() == country
                        && d.getCity() == city
                        && d.getStreet() == street){
                    System.err.println("Dress rental with this parameters is already exist!");
                    return;
                }
            }
        }
        persist(dressRental);
        System.out.println("Dress rental has been created");
    }

    public void deleteDress(int id){
        System.out.println("Deleting a dress rental...............");
        DressRental d = find(id);
        if(d == null){
            System.err.println("Dress rental with id " + id + " has not been found!");
            return;
        }
        delete(d);
    }

    public void setName(int id, String name){
        DressRental d = find(id);
        if(d == null){
            System.err.println("Dress rental with id " + id + " has not been found!");
            return;
        }
        d.setName(name);
        update(d);
        System.out.println("Dress has been deleted");
    }

    public void setStreet(int id, String street){
        DressRental d = find(id);
        if(d == null){
            System.err.println("Dress rental with id " + id + " has not been found!");
            return;
        }
        d.setStreet(street);
        update(d);
    }

    public DressRental find(DressRental dressRental){ return (DressRental) dao.findById(dressRental.getId()); }

    public List<DressRental> getAllDressRentalsByName(String name){ return dao.findAllDressRentalsWithName(name); }
}
