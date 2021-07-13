package com.example.crudproject.service;

import com.example.crudproject.exception.PersonNotFoundException;
import com.example.crudproject.model.Person;
import com.example.crudproject.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Autowired
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
    public Person getPersonById(int id) {
        return personRepository.findPersonById(id)
                .orElseThrow(() -> new PersonNotFoundException("User with this id wasn't found"));
    }

    @Override
    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void deletePerson(int personId) {
        personRepository.deletePersonById(personId);
    }
}
