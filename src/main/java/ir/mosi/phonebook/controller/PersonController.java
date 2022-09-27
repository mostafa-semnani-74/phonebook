package ir.mosi.phonebook.controller;

import ir.mosi.phonebook.model.entity.domain.Person;
import ir.mosi.phonebook.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {
    Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @GetMapping("findAll")
    public List<Person> findAll() {
        List<Person> personList = personService.findAll();
        logger.info("personList with size " + personList.size() + " found successfully");
        return personList;
    }

    @PostMapping("save")
    public Person save(@RequestBody Person person) {
        Person savedPerson = personService.save(person);
        logger.info("Person with id " + savedPerson.getPersonId() + " and with last name " + person.getLastName()
                + " created successfully");
        return savedPerson;
    }

    @PostMapping("update")
    public Person update(@RequestBody Person person) {
        Person updatedPerson = personService.update(person);
        logger.info("Person with id " + updatedPerson.getPersonId() + " updated successfully");
        return updatedPerson;
    }

}
