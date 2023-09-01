package ir.mostafa.semnani.phonebook.model.repository.impl;

import ir.mostafa.semnani.phonebook.model.entity.Person;
import ir.mostafa.semnani.phonebook.model.repository.PersonRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PersonRepositoryImpl implements PersonRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> findAll() {
        TypedQuery<Person> findAllQuery = entityManager.createQuery("SELECT P FROM Person P", Person.class);
        return findAllQuery.getResultList();
    }

    public Person findById(Long id) {
        return entityManager.find(Person.class, id);
    }

    public void save(Person person) {
        entityManager.persist(person);
    }

    public Person update(Person person) {
        Person personEntity = findById(person.getId());

        personEntity.setName(person.getName());
        entityManager.persist(personEntity);
        return personEntity;
    }

    public void delete(Long id) {
        Person personEntity = findById(id);

        entityManager.remove(personEntity);
    }

}
