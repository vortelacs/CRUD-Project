package com.example.crudproject;

import com.example.crudproject.model.DTO.PersonDTO;
import com.example.crudproject.model.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDTO personToDTO(Person person);

    Person DTOToPerson(PersonDTO dto);
}
