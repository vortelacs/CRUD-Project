package com.example.crudproject.service;

import com.example.crudproject.PersonMapper;
import com.example.crudproject.exception.PersonNotFoundException;
import com.example.crudproject.model.DTO.PersonDTO;
import com.example.crudproject.model.Person;
import com.example.crudproject.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private PersonMapper personMapper;

    @InjectMocks
    private PersonService personService;


    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Disabled
    public void getPersons() {
        List<PersonDTO> personsDTO = new ArrayList<>();
        personsDTO.add(new PersonDTO(144, "aaaa", "bbbb", "cccc"));
        personsDTO.add(new PersonDTO(123, "aaaa", "bbbb", "cccc"));
        personsDTO.add(new PersonDTO(200, "aaaa", "bbbb", "cccc"));

        List<Person> persons = new ArrayList<>();
        persons.add(new Person(144, "aaaa", "bbbb", "cccc"));
        persons.add(new Person(123, "aaaa", "bbbb", "cccc"));
        persons.add(new Person(200, "aaaa", "bbbb", "cccc"));

        when(personRepository.findAll()).thenReturn(persons);

        List<PersonDTO> resultedValue = personService.getPersons();

        assertEquals(personsDTO.size(), resultedValue.size());
    }

    @Test
    public void getPersonById() {

        PersonDTO personDTO = new PersonDTO(144, "aaaa", "bbbb", "cccc");
        Person person = new Person(144, "aaaa", "bbbb", "cccc");

        when(personRepository.findPersonById(person.getId())).thenReturn(java.util.Optional.of(person));
        when(personMapper.personToDTO(person)).thenReturn(personDTO);

        PersonDTO result = personService.getPersonById(person.getId());

        verify(personRepository).findPersonById(person.getId());
        verify(personMapper).personToDTO(person);
        assertEquals(personDTO, result);
    }

    @Test(expected = PersonNotFoundException.class)
    public void throwExceptionIfNoPersonWithGivenIdIsFoundInFindMethod(){
        Person person = new Person(144, "aaaa", "bbbb", "cccc");
        when(personRepository.findPersonById(person.getId())).thenReturn(Optional.of(person));

        personService.getPersonById(person.getId()+1);

    }

    @Test
    public void addPerson() {

        PersonDTO personDTO = new PersonDTO(144, "aaaa", "bbbb", "cccc");
        Person person = new Person(144, "aaaa", "bbbb", "cccc");

        when(personMapper.DTOToPerson(personDTO)).thenReturn(person);
        when(personMapper.personToDTO(person)).thenReturn(personDTO);
        when(personRepository.save(person)).thenReturn(person);

        PersonDTO result = personService.addPerson(personDTO);
        Person expected = personMapper.DTOToPerson(result);
        verify(personRepository).save(person);
        assertNotNull(result);
        assertEquals(person, expected);


    }

    @Test
    public void updatePerson() {
        PersonDTO personDTO = new PersonDTO(144, "aaaa", "bbbb", "cccc");
        Person person = new Person(144, "aaaa", "bbbb", "cccc");

        when(personRepository.save(person)).thenReturn(person);
        when(personRepository.findPersonById(person.getId())).thenReturn(Optional.of(person));
        when(personMapper.DTOToPerson(personDTO)).thenReturn(person);
        when(personMapper.personToDTO(person)).thenReturn(personDTO);
        PersonDTO result = personService.updatePerson(personDTO.getId(),personDTO);

        assertEquals(personDTO, result);
    }

    @Test(expected = PersonNotFoundException.class)
    public void throwExceptionIfNoPersonWithGivenIdIsFoundInUpdateMethod() {
        Person person = new Person(144, "aaaa", "bbbb", "cccc");

        when(personRepository.findPersonById(person.getId())).thenReturn(Optional.of(person));
        personService.getPersonById(person.getId() + 1);
    }

    @Test
    public void deletePerson() {
        Person person = new Person(144, "aaaa", "bbbb", "cccc");

        personService.deletePerson(person.getId());
        verify(personRepository, times(1)).deleteById(person.getId());

    }
}