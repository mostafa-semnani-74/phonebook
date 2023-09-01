package ir.mostafa.semnani.phonebook.model.service;

import ir.mostafa.semnani.phonebook.model.entity.Person;

import java.util.List;

public interface PersonService {
    List<Person> findAll();

    Person findById(Long id);

    void save(Person person);

    Person update(Person person);

    void delete(Long id);
}
