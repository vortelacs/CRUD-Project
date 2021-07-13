package com.example.crudproject.controller;

import com.example.crudproject.model.Person;
import com.example.crudproject.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequestMapping(path="/persons")
public class MainController {

    @Autowired
    private PersonServiceImpl personService;

    @GetMapping(path="/all")
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> personList = personService.getPersons();
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") int id) {
         Person foundedPerson = personService.getPersonById(id);
         return new ResponseEntity<>(foundedPerson, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Person> addPerson(@RequestBody Person person){
        Person newPerson = personService.addPerson(person);
        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person){
        Person updatedPerson = personService.updatePerson(person);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable int id){
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}