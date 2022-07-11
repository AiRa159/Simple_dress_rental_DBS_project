package com.example.project.service;

import com.example.project.model.Client;
import com.example.project.model.Creator;
import com.example.project.model.Dress;
import com.example.project.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class SystemInitializer {

    private final ClientService clientService;
    private final CreatorService creatorService;
    private final DressRentalService dressRentalService;
    private final DressService dressService;

    @Autowired
    public SystemInitializer(ClientService clientService, CreatorService creatorService, DressRentalService dressRentalService, DressService dressService) {
        this.clientService = clientService;
        this.creatorService = creatorService;
        this.dressRentalService = dressRentalService;
        this.dressService = dressService;
    }

    @PostConstruct
    public void initSystem() {
//        Create new person
        clientService.create(1, "Aiya", "Ra");

//        Try add new person with same personal id
//        Error : Person with id 1 is already exists
        clientService.create(1, "Dias", "Ka");

//        Change surname of p
        clientService.changeSurname(1, "Rakhimova");

//        Add email 1
        clientService.addEmail(1, "aiya@seznam.cz");
//        Try add an existing mail
//        Error : This email is already exists
        clientService.addEmail(1, "aiya@seznam.cz");
//        Add email 2
        clientService.addEmail(1, "aiya@google.com");


//        Create creators
        creatorService.create(2, "Anna", "Maria");
        creatorService.create(3, "Barbora", "Beránova");

//        Create dresses
//        Error : Wrong data!
        dressService.createNewDress(0, "");
        dressService.createNewDress(34, "boho");
        dressService.createNewDress(32, "monochrome");
        dressService.createNewDress(36, "amour");

//        Adding dress to creator
        creatorService.addDress(2, 1);
        creatorService.addDress(2, 3);
        dressService.getCreatorsList(1);

//        Deleting dress from creators list
        creatorService.deleteDress(2, 1);
        dressService.getCreatorsList(1);

//        Adding dress to creators
        creatorService.addDress(2, 2);
        creatorService.addDress(3, 2);
        dressService.getCreatorsList(2);

//        Deleting dress from db
        dressService.deleteDress(2);
    }
}
