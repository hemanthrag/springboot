package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    Person insertPerson( Person person);

    default Person addPerson(Person person){
        return insertPerson(person);
    }

    List<Person> getPersonsData();

    Optional<Person> selectPersonById(int id);

    int deletePeronById(int id);

    ResponseEntity< Person > updatePersonById(int id , Person person);
}
