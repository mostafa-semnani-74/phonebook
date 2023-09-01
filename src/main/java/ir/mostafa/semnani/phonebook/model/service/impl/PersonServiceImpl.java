package ir.mostafa.semnani.phonebook.model.service.impl;

import ir.mostafa.semnani.phonebook.model.entity.Person;
import ir.mostafa.semnani.phonebook.model.repository.PersonRepository;
import ir.mostafa.semnani.phonebook.model.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@AllArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private final PersonRepository personRepository;

    @Transactional(readOnly = true)
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public void save(Person person) {
        personRepository.save(person);
    }

}
