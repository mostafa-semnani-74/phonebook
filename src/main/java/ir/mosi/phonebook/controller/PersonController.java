package ir.mosi.phonebook.controller;

import ir.mosi.phonebook.model.entity.domain.Person;
import ir.mosi.phonebook.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
@RequiredArgsConstructor
public class PersonController {

    private PersonService personService;

    @GetMapping("findAll")
    public List<Person> findAll() {
        return personService.findAll();
    }

    @PostMapping("save")
    public Person save(@RequestBody Person person) {
        return personService.save(person);
    }

    @PostMapping("update")
    public Person update(@RequestBody Person person) {
        return personService.save(person);
    }
}
