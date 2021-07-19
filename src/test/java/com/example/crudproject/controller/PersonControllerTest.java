package com.example.crudproject.controller;

import com.example.crudproject.model.DTO.PersonDTO;
import com.example.crudproject.service.PersonService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PersonControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    @Before
    public void setUp(){
        objectMapper = new ObjectMapper();
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }

    @Test
    public void getAllPersons() throws Exception {
            List<PersonDTO> persons = new ArrayList<>();
            persons.add(new PersonDTO(144, "aaaa", "bbbb", "cccc"));
            persons.add(new PersonDTO(123, "aaaa", "bbbb", "cccc"));
            persons.add(new PersonDTO(200, "aaaa", "bbbb", "cccc"));

            when(personService.getPersons()).thenReturn(persons);

            MvcResult mvcResult = mockMvc.perform(get("/persons"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andReturn();
            List<PersonDTO> personDTOS = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<PersonDTO>>() {
            });
            assertEquals(persons, personDTOS);
    }

    @Test
    public void getPersonById() throws Exception {
        int id = 144;
        PersonDTO person = new PersonDTO(144, "aaaa", "bbbb", "cccc");
        when(personService.getPersonById(id)).thenReturn(person);

        mockMvc.perform(get("/persons/144"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(person.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(person.getLastName()))
                .andExpect(jsonPath("$.id").value(person.getId()))
                .andExpect(jsonPath("$.email").value(person.getEmail()));
    }

    @Test
    public void addPerson() throws Exception {

        PersonDTO person = new PersonDTO(144, "aaaa", "bbbb", "cccc");
        when(personService.addPerson(person)).thenReturn(person);

        String jsonContent = "{\n" +
                "  \"id\": 144,\n" +
                "  \"firstName\": \"aaaa\",\n" +
                "  \"lastName\": \"bbbb\",\n" +
                "  \"email\": \"cccc\"\n" +
                "}";

        mockMvc.perform(post("/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value(person.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(person.getLastName()))
                .andExpect(jsonPath("$.id").value(person.getId()))
                .andExpect(jsonPath("$.email").value(person.getEmail()));
    }

    @Test
    public void updatePerson() throws Exception {

        int id = 144;
        PersonDTO person = new PersonDTO(144, "aaaa", "bbbb", "cccc");
        when(personService.updatePerson(id, person)).thenReturn(person);

        String jsonContent = "{\n" +
                "  \"id\": 144,\n" +
                "  \"firstName\": \"aaaa\",\n" +
                "  \"lastName\": \"bbbb\",\n" +
                "  \"email\": \"cccc\"\n" +
                "}";

        mockMvc.perform(put("/persons/144")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(person.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(person.getLastName()))
                .andExpect(jsonPath("$.id").value(person.getId()))
                .andExpect(jsonPath("$.email").value(person.getEmail()));
    }

    @Test
    public void deletePerson() throws Exception {
        int id = 144;
        mockMvc.perform(delete("/persons/144"))
                .andExpect(status().isOk());

        verify(personService, times(1)).deletePerson(id);
    }
}