package ir.mostafa.semnani.phonebook.controller;

import ir.mostafa.semnani.phonebook.model.entity.Person;
import ir.mostafa.semnani.phonebook.model.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/persons")
@AllArgsConstructor
public class PersonController {
    @Autowired
    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
        List<Person> persons = personService.findAll();
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person) {
        personService.save(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Person> update(@RequestBody Person person) {
        return ResponseEntity.ok(personService.update(person));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.ok().build();
    }

}
