package com.example.crudproject.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class PersonDTO {
    private int id;
    private String  firstName;
    private String  lastName;
    private String  email;
}
