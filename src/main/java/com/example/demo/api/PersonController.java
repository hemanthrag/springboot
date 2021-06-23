package com.example.demo.api;

import com.example.demo.model.DeleteMessage;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @PostMapping
    public Person addPerson(@Valid @NotNull @RequestBody Person person){
        return personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPerson(){
        return personService.getPersonDetails();
    }

    @GetMapping(path = "/getuser/{id}")
    public Person selectPersonById(@PathVariable("id") int id) {
        return personService.selectPersonById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id not found"));
    }

    @DeleteMapping(path = "/deleteUser/{id}")
    public DeleteMessage deletePersonById(@PathVariable("id") int id){
//        if(selectPersonById(id) != null){
//            personService.deletePersonById(id);
//            DeleteMessage deleteMessage = new DeleteMessage();
//            deleteMessage.setId(id);
//            deleteMessage.setMessage("Deleted Successfully");
//            return deleteMessage;
//        }else {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id not found");
//        }
        return personService.deletePersonById(id);


    }

    @PutMapping(path = "/updateUser/{id}")
    public void updatePersonById(@PathVariable("id") int id , @Valid @NotNull @RequestBody Person person){
        personService.updatePerson(id,person);
    }
}
