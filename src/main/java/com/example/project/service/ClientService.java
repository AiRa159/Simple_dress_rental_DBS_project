package com.example.project.service;

import com.example.project.dao.BaseDao;
import com.example.project.dao.PersonDao;
import com.example.project.model.Client;
import com.example.project.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientService extends BaseService<Client> implements PersonServiceImp{

    private final PersonDao dao;

    @Autowired
    public ClientService(PersonDao dao) { this.dao = dao;}

    @Override
    protected BaseDao<Client> getDao() {
        return this.dao;
    }

    @Override
    public void create(int personal_id, String name, String surname) {
        System.out.println("Creating a client...............");
        if(find(personal_id) != null){
            System.err.println("Client with personal id " + personal_id + " already exists!");
            return;
        }
        if(personal_id == 0 || name.equals("") || surname.equals("")){
            System.err.println("Wrong data!");
            return;
        }
        Client client = new Client();
        client.setPersonal_id(personal_id);
        client.setFirst_n(name);
        client.setLast_n(surname);
        persist(client);
        System.out.println("Client has been created");
    }

    @Override
    public void delete(int id) {
        System.out.println("Deleting a client...............");
        Person p = find(id);
        if(p == null){
            System.err.println("Client with personal id " + id + " has not been found!");
            return;
        }
        delete((Client) p);
        System.out.println("Client has been deleted");
    }

    @Override
    public void changeName(int id, String newName) {
        System.out.println("Changing a client data...............");
        Person p = find(id);
        if(p == null){
            System.err.println("Client with personal id " + id + " has not been found!");
            return;
        }
        if(newName.equals("") || newName == null){
            System.err.println("Wrong data!");
            return;
        }
        p.setFirst_n(newName);
        update((Client) p);
        System.out.println("Clients data has been changed");
    }

    @Override
    public void changeSurname(int id, String newSurname) {
        System.out.println("Changing a client data...............");
        Person p = find(id);
        if(p == null){
            System.err.println("Clients with personal id " + id + " has not been found!");
            return;
        }
        if(newSurname.equals("") || newSurname == null){
            System.err.println("Wrong data!");
            return;
        }
        p.setLast_n(newSurname);
        update((Client) p);
        System.out.println("Clients data has been changed");
    }

    @Override
    public Client find(Person person) {
        return (Client) dao.findById(person.getPersonal_id());
    }

    public void setSize(int id, int size){
        System.out.println("Changing a client data...............");
        Client p = find(id);
        if(p == null){
            System.err.println("Client with personal id " + id + " has not been found!");
            return;
        }
        if(size == 0){
            System.err.println("Wrong data!");
            return;
        }
        p.setSize(size);
        update(p);
        System.out.println("Clients data has been changed");
    }

    public void addEmail(int id, String email){
        System.out.println("Adding email...............");
        Client p = find(id);
        if(p == null){
            System.err.println("Client with personal id " + id + " has not been found!");
            return;
        }
        if(email == ""){
            System.err.println("Wrong data");
            return;
        }
        if(p.getEmails().contains(email)){
            System.err.println("Email is already exists");
            return;
        }
        p.addEmail(email);
        update(p);
        System.out.println("Email has been added");
    }

    public void deleteEmail(int id, String email){
        Client p = find(id);
        if(p == null){
            System.err.println("Client with personal id " + id + " has not been found!");
            return;
        }
        if(email == ""){
            System.err.println("Wrong data");
            return;
        }
        if(!p.getEmails().contains(email)){
            System.err.println("The client does not have such an email");
            return;
        }
        p.removeEmail(email);
        update(p);
        System.out.println("Email has been deleted");
    }

}
