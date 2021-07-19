package com.example.crudproject.service;

import com.example.crudproject.PersonMapper;
import com.example.crudproject.exception.PersonNotFoundException;
import com.example.crudproject.model.DTO.PersonDTO;
import com.example.crudproject.model.Person;
import com.example.crudproject.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))

public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;


    public List<PersonDTO> getPersons() {
        return personRepository.findAll().stream()
                .map(personMapper::personToDTO)
                .collect(Collectors.toList());

    }

    public PersonDTO getPersonById(int id) {
        return personMapper.personToDTO(personRepository.findPersonById(id)
                .orElseThrow(() -> new PersonNotFoundException("User with this id wasn't found")));
    }

    public PersonDTO addPerson(PersonDTO person) {
        Person personToBeSaved = personMapper.DTOToPerson(person);

        Person savedPerson = personRepository.save(personToBeSaved);

        return personMapper.personToDTO(savedPerson);

    }

    public PersonDTO updatePerson(int id, PersonDTO body) {
        Person foundPerson = personRepository.findPersonById(id)
                .orElseThrow(() -> new EntityNotFoundException("Couldn't find person at id: " + id));

        Person savedPerson = personRepository.save(foundPerson);
        return personMapper.personToDTO(savedPerson);
    }

    public void deletePerson(int personId) {
        personRepository.deleteById(personId);
    }
}
