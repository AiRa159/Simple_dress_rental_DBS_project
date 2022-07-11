package com.example.project.service;

import com.example.project.model.Person;

public interface PersonServiceImp {

    public void create(int personal_id, String name, String surname);

    public void delete(int id);

    public void changeName(int id, String newName);

    public void changeSurname(int id, String newSurname);

    public Person find(Person person);
}
