package ir.mostafa.semnani.phonebook.model.service;

import ir.mostafa.semnani.phonebook.model.entity.Person;

import java.util.List;

public interface PersonService {
    List<Person> findAll();

    void save(Person person);
}
