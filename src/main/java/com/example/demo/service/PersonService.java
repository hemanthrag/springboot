package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.DatabaseSequence;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class PersonService {
    PersonDao personDao;

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    public PersonService(@Qualifier("fakeDao") PersonDao personDao){
        this.personDao = personDao;
    }

    public int addPerson(Person person){
        person.setId(getSequenceNumber(Person.SEQUENCE_NAME));
        return personDao.addPerson(person);
    }

    public List<Person> getPersonDetails(){
        return personDao.getPersonsData();
    }

    public Optional<Person> selectPersonById(int id) {
        return personDao.selectPersonById(id);
    }

    public int deletePersonById(int id){
        return personDao.deletePeronById(id);
    }

    public ResponseEntity< Person > updatePerson(int id, Person person){
        return personDao.updatePersonById(id,person);
    }

    public int getSequenceNumber(String sequenceName){
        Query query = new Query(Criteria.where("id").is(sequenceName));
        Update update = new Update().inc("seq",1);
        DatabaseSequence counter = mongoOperations.findAndModify(query,update,options().returnNew(true).upsert(true), DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
