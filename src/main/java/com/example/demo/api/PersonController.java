package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
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
    public int addPerson(@Valid @NotNull @RequestBody Person person){
        return personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPerson(){
        return personService.getPersonDetails();
    }

    @GetMapping(path = "/getuser/{id}")
    public Person selectPersonById(@PathVariable("id") UUID id){
        return personService.selectPersonById(id).orElse(null);
    }

    @DeleteMapping(path = "/deleteUser/{id}")
    public void deletePersonById(@PathVariable("id") UUID id){
        personService.deletePersonById(id);
    }

    @PutMapping(path = "/updateUser/{id}")
    public void updatePersonById(@PathVariable("id") UUID id , @Valid @NotNull @RequestBody Person person){
        personService.updatePerson(id,person);
    }
}
