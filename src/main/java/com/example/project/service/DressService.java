package com.example.project.service;

import com.example.project.dao.BaseDao;
import com.example.project.dao.DressDao;
import com.example.project.dao.PersonDao;
import com.example.project.model.Creator;
import com.example.project.model.Dress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DressService extends BaseService<Dress>{
    private final DressDao dao;
    private final PersonDao personDao;

    @Autowired
    public DressService(DressDao dao, PersonDao personDao) {
        this.dao = dao;
        this.personDao = personDao;
    }

    @Override
    protected BaseDao<Dress> getDao() {
        return this.dao;
    }

    public void createNewDress(int size, String style){
        System.out.println("Creating a dress...............");
        if(size == 0 || style.equals("")){
            System.err.println("Wrong data!");
            return;
        }
        Dress dress = new Dress();
        dress.setDress_size(size);
        dress.setDress_style(style);
        persist(dress);
        System.out.println("Dress has been created");
    }

    public void deleteDress(int id){
        System.out.println("Deleting a dress...............");
        Dress d = find(id);
        if(d == null){
            System.err.println("Dress with serial number " + id + " has not been found!");
            return;
        }
        List<Creator> creators = personDao.getAllCreators();
        if(!creators.isEmpty()){
            for(Creator c : creators){
                if(c.getDresses().contains(d)){
                    c.removeDress(d);
                    personDao.update(c);
                }
            }
        }
        delete(d);
        System.out.println("Dress has been deleted");
    }

    public void getCreatorsList(int id){
        Dress d = find(id);
        if(d == null){
            System.err.println("Dress with serial number " + id + " has not been found!");
            return;
        }
        if(d.getCreatorList().isEmpty()){
            System.out.println("List is empty");
            return;
        }
        d.printArray();
    }

    public Dress find(Dress dress){ return (Dress) dao.findById(dress.getId()); }
}
