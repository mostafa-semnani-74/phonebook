package ir.mostafa.semnani.phonebook.model.repository;

import ir.mostafa.semnani.phonebook.model.entity.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> findAll();

    void save(Person person);
}
