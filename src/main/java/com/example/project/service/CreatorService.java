package com.example.project.service;

import com.example.project.dao.BaseDao;
import com.example.project.dao.DressDao;
import com.example.project.dao.PersonDao;
import com.example.project.model.Creator;
import com.example.project.model.Dress;
import com.example.project.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CreatorService extends BaseService<Creator> implements PersonServiceImp{

    private final PersonDao dao;
    private final DressDao dressDao;

    @Autowired
    public CreatorService(PersonDao dao, DressDao dressDao) {
        this.dao = dao;
        this.dressDao = dressDao;
    }

    @Override
    protected BaseDao<Creator> getDao() {
        return this.dao;
    }

    @Override
    public void create(int personal_id, String name, String surname) {
        System.out.println("Creating a creator...............");
        if(find(personal_id) != null){
            System.err.println("Creator with personal id " + personal_id + " already exists!");
            return;
        }
        if(personal_id == 0 || name.equals("") || surname.equals("")){
            System.err.println("Wrong data!");
            return;
        }
        Creator creator = new Creator();
        creator.setPersonal_id(personal_id);
        creator.setFirst_n(name);
        creator.setLast_n(surname);
        persist(creator);
        System.out.println("Creator has been created");
    }

    @Override
    public void delete(int id) {
        System.out.println("Deleting a creator...............");
        Person p = find(id);
        if(p == null){
            System.err.println("Creator with personal id " + id + " has not been found!");
            return;
        }
        delete((Creator) p);
        System.out.println("Creator has been deleted");
    }

    @Override
    public void changeName(int id, String newName) {
        System.out.println("Changing a creator data...............");
        Person p = find(id);
        if(p == null){
            System.err.println("Creator with personal id " + id + " has not been found!");
            return;
        }
        if(newName.equals("") || newName == null){
            System.err.println("Wrong data!");
            return;
        }
        p.setFirst_n(newName);
        update((Creator) p);
        System.out.println("Creators data has been changed");
    }

    @Override
    public void changeSurname(int id, String newSurname) {
        System.out.println("Changing a creator data...............");
        Person p = find(id);
        if(p == null){
            System.err.println("Creator with personal id " + id + " has not been found!");
            return;
        }
        if(newSurname.equals("") || newSurname == null){
            System.err.println("Wrong data!");
            return;
        }
        p.setLast_n(newSurname);
        update((Creator) p);
        System.out.println("Creators data has been changed");
    }

    public void addDress(int creator_id, int dress_serial_number){
        System.out.println("Adding a dress...............");
        Creator creator = find(creator_id);
        Dress dress = (Dress) dressDao.findById(dress_serial_number);
        if(creator == null){
            System.err.println("Creator with personal id " + creator_id + " has not been found!");
            return;
        }
        if(dress == null){
            System.err.println("Dress with serial number " + dress_serial_number + " has not been found!");
            return;
        }
        creator.addDress(dress);
        System.out.println("Dress has been added");
    }

    public void deleteDress(int creator_id, int dress_serial_number){
        System.out.println("Deleting a dress...............");
        Creator creator = find(creator_id);
        Dress dress = (Dress) dressDao.findById(dress_serial_number);
        if(creator == null){
            System.err.println("Creator with personal id " + creator_id + " has not been found!");
            return;
        }
        if(dress == null){
            System.err.println("Dress with serial number " + dress_serial_number + " has not been found!");
            return;
        }
        creator.removeDress(dress);
        System.out.println("Dress has been deleted");
    }

    @Override
    public Creator find(Person person) {
        return (Creator) dao.findById(person.getPersonal_id());
    }

    public void getDressList(int id){
        Creator c = find(id);
        if(c == null){
            System.err.println("Creator with serial number " + id + " has not been found!");
            return;
        }
        if(c.getDresses().isEmpty()){
            System.out.println("List is empty");
            return;
        }
        c.printArray();
    }

    public List<Creator> getAllCreators(){
        return dao.getAllCreators();
    }
}
