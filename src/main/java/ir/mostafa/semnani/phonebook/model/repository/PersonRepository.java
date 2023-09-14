package ir.mostafa.semnani.phonebook.model.repository;

import ir.mostafa.semnani.phonebook.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
