package com.example.crudproject.service;

import com.example.crudproject.model.Person;
import com.example.crudproject.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    @Override
    public List<Person> getPersons() {
        List<Person> persons = new ArrayList<>();
        personRepository.findAll().forEach(persons::add);
        return persons;
    }

    @Override
    public Person getPersonById(Long id) {
        return null;
    }

    @Override
    public Person insert(Person person) {
        return null;
    }

    @Override
    public void updatePerson(Long id, Person person) {

    }

    @Override
    public void deletePerson(Long PersonId) {

    }
}
