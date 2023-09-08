package ir.mostafa.semnani.phonebook.model.repository.impl;

import ir.mostafa.semnani.phonebook.model.entity.Person;
import ir.mostafa.semnani.phonebook.model.repository.BaseRepository;
import ir.mostafa.semnani.phonebook.model.repository.PersonRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepositoryImpl extends BaseRepository<Person> implements PersonRepository {
    public List<Person> findAll() {
        return super.findAll("SELECT P FROM Person P", Person.class);
    }

    public Person findById(Long id) {
        return super.findById(Person.class, id);
    }

    public void save(Person person) {
        super.save(person);
    }

    public Person update(Person person) {
        super.save(person);
        return person;
    }

    public void delete(Person person) {
        super.delete(person);
    }

}
