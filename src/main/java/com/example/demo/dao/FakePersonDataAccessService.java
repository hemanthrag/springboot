package com.example.demo.dao;

import com.example.demo.model.Person;
import com.example.demo.repo.PersonRepository;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    @Autowired
    PersonRepository personRepository;

    List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson( Person person) {
        personRepository.save(person);
//        DB.add(new Person(id,person.getName()));
        return person.getId();
    }

    @Override
    public List<Person> getPersonsData() {
        return personRepository.findAll();
    }


    @Override
    public Optional<Person> selectPersonById(int id) {
        return personRepository.findById(id);
    }

    @Override
    public int deletePeronById(int id) {
        personRepository.deleteById(id);
        return id;
    }

    @Override
    public ResponseEntity < Person > updatePersonById(int id, Person personUpdate) {
        Person person = personRepository.findById(id)
                .orElse(personUpdate);

        person.setName(personUpdate.getName());
        final Person updatedEmployee = personRepository.save(person);
        return ResponseEntity.ok(updatedEmployee);
//        return selectPersonById(idUpdate).map(person -> {
//            int indexOfPersonToUpdate = DB.indexOf(person);
//            if(indexOfPersonToUpdate >= 0){
//                DB.set(indexOfPersonToUpdate,new Person(idUpdate,personUpdate.getName()));
//                return 1;
//            }
//            return 0;
//        }).orElse(0);
    }
}
