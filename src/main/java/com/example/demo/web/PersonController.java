package com.example.demo.web;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entities.Person;
import com.example.demo.services.PersonService;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;

//@Controller
@RestController
@RequestMapping("/api/persons")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/")
    public List<Person> getAllPersons() {
	return personService.getAllPersonsFiltered();
    }

    @PostMapping("/check")
    public Person checkPerson(@RequestBody Person person) {
	return personService.getPersonByEmailAndPassword(person.getEmail(), person.getPassword());
    }

    @PostMapping("/emailcheck")
    public Person getPersonByEmail(@RequestBody Person person) {
        String mail = person.getEmail();
        return personService.getPersonByEmail(mail);
    }


//    @GetMapping("/email/{email}")
//    public Person getPersonByEmail(@PathVariable String email) {
//	return personService.getPersonByEmail(email);
//    }

    @PostMapping("/updatedb")
    public ResponseEntity<Person> updateDataBase(@RequestBody Person person) {
	Person savedPerson = personService.updateDataBase(person);
	return new ResponseEntity<>(savedPerson, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
	Person newPerson = personService.createPerson(person);
	return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable String id) {
	return personService.getPersonById(id);
    }

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
	return new ResponseEntity<>(personService.createPerson(person), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Person updatePerson(@RequestBody Person personDetails) {
	return personService.updatePerson(personDetails);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable String id) {
	Person person = personService.deletePerson(id);
	return new ResponseEntity<>(person, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/family/{id}")
    public ResponseEntity<Set<Person>> getFamily(@PathVariable String id) {
	return new ResponseEntity<>(personService.getFamily(id), HttpStatus.OK);
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<Map<String, String>> sendEmail(@RequestBody Map<String, Person> persons)
	    throws MessagingException {
	Person initiator = persons.get("initiator");
	Person person = persons.get("person");
	return personService.sendEmail(initiator, person);
    }
}