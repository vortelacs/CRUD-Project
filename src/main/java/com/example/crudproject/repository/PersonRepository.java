package com.example.crudproject.repository;

import com.example.crudproject.model.Person;
import org.springframework.data.repository.CrudRepository;


public interface PersonRepository extends CrudRepository<Person, Integer> {

}