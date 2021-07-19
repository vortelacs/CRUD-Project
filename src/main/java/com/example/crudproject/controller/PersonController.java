package com.example.crudproject.controller;

import com.example.crudproject.model.DTO.PersonDTO;
import com.example.crudproject.model.Person;
import com.example.crudproject.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="/persons")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping(path="")
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        List<PersonDTO> personList = personService.getPersons();
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable("id") int id) {
         PersonDTO foundedPerson = personService.getPersonById(id);
         return new ResponseEntity<>(foundedPerson, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<PersonDTO> addPerson(@RequestBody PersonDTO person){
        PersonDTO newPerson = personService.addPerson(person);
        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable int id, @RequestBody PersonDTO body){
        PersonDTO updatedPerson = personService.updatePerson(id, body);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable int id){
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}