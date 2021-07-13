package com.example.crudproject.repository;

import com.example.crudproject.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface PersonRepository extends CrudRepository<Person, Integer> {

    void deletePersonById(int personId);

    Optional<Person> findPersonById(int id);
}