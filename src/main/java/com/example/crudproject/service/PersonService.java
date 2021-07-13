package com.example.crudproject.service;

import com.example.crudproject.model.Person;

import java.util.List;

public interface PersonService {


        List<Person> getPersons();

        Person getPersonById(int id);

        Person addPerson(Person person);

        Person updatePerson(Person person);

        void deletePerson(int personId);


}
