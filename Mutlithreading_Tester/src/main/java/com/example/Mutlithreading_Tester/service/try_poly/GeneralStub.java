package com.example.Mutlithreading_Tester.service.try_poly;

import com.example.Mutlithreading_Tester.entity.Person;
import com.example.Mutlithreading_Tester.enums.Nations;
import com.example.Mutlithreading_Tester.service.dto.request.PersonRequestDTO;

import java.util.List;

public interface GeneralStub<T> {
    public List<T> nationality(Nations nationality);
    public T person(PersonRequestDTO personRequestDTO);
    public List<T> peopleEmail(Nations nationality);
}
