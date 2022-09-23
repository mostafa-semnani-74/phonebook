package ir.mosi.phonebook.model.repository;

import ir.mosi.phonebook.model.entity.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
