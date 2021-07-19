package com.example.crudproject.repository;

import com.example.crudproject.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    //void deletePersonById(int personId);

    Optional<Person> findPersonById(int id);
}