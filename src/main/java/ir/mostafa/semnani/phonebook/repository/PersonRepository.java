package ir.mostafa.semnani.phonebook.repository;

import ir.mostafa.semnani.phonebook.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, QuerydslPredicateExecutor<Person> {

    Optional<Person> findByAppUserId(Long appUserId);

}
