package ir.mosi.phonebook.service;

import ir.mosi.phonebook.model.entity.domain.Person;
import ir.mosi.phonebook.model.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private PersonRepository personRepository;

    @Transactional(readOnly = true)
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Transactional
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Transactional
    public Person update(Person person) {
        return save(person);
    }

}
