package ir.mostafa.semnani.phonebook.model.repository;

import ir.mostafa.semnani.phonebook.model.entity.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> findAll();

    Person findById(Long id);

    void save(Person person);

    Person update(Person person);

    void delete(Person person);
}
