package com.example.demo.dao;

import com.example.demo.model.Person;
import com.example.demo.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public int insertPerson(UUID id, Person person) {
        personRepository.save(new Person(id,person.getName()));
//        DB.add(new Person(id,person.getName()));
        return 1;
    }

    @Override
    public List<Person> getPersonsData() {
        return personRepository.findAll();
    }


    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findAny();
    }

    @Override
    public int deletePeronById(UUID id) {
        Optional<Person> personPresentOrNot = selectPersonById(id);
        if(personPresentOrNot.isEmpty()){
            return 0;
        }
        DB.remove(personPresentOrNot.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID idUpdate, Person personUpdate) {
        return selectPersonById(idUpdate).map(person -> {
            int indexOfPersonToUpdate = DB.indexOf(person);
            if(indexOfPersonToUpdate >= 0){
                DB.set(indexOfPersonToUpdate,new Person(idUpdate,personUpdate.getName()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }
}
