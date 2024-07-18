package ir.mostafa.semnani.phonebook.service.impl;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import ir.mostafa.semnani.phonebook.dto.PageDTO;
import ir.mostafa.semnani.phonebook.dto.PersonCriteriaDTO;
import ir.mostafa.semnani.phonebook.entity.QPerson;
import ir.mostafa.semnani.phonebook.exception.PersonNotFoundException;
import ir.mostafa.semnani.phonebook.dto.PersonDTO;
import ir.mostafa.semnani.phonebook.entity.Person;
import ir.mostafa.semnani.phonebook.mapper.PersonMapper;
import ir.mostafa.semnani.phonebook.repository.PersonRepository;
import ir.mostafa.semnani.phonebook.service.AddressService;
import ir.mostafa.semnani.phonebook.service.PersonNotificationService;
import ir.mostafa.semnani.phonebook.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonNotificationService notificationService;
    private final AddressService addressService;

    private final PersonMapper personMapper;

    private static final ExecutorService executorService = Executors.newFixedThreadPool(2);

    @Transactional(readOnly = true)
    public Page<PersonDTO> findAll(PageDTO pageDTO, PersonCriteriaDTO personCriteriaDTO) {
        Pageable pageable = PageRequest.of(pageDTO.pageNumber(), pageDTO.size());

        QPerson qPerson = QPerson.person;
        BooleanExpression isAdult = qPerson.age.goe(18);
        BooleanExpression nameStartWith = qPerson.name.startsWith(personCriteriaDTO.getName());

        Page<Person> personList = personRepository.findAll(isAdult.and(nameStartWith), pageable);

        log.info("{} persons found", personList.getTotalElements());
        return new PageImpl<>(
                personList.stream()
                        .map(personMapper::toDTO)
                        .collect(Collectors.toList()),
                pageable,
                personList.getTotalElements()
        );
    }

    @Transactional(readOnly = true)
    public PersonDTO findById(Long id) {
        Person person = findEntityById(id);
        log.info("person with id : {} found", person.getId());
        return personMapper.toDTO(person);
    }

    public PersonDTO save(PersonDTO personDTO) {
        Person savedPerson = personRepository.save(personMapper.toEntity(personDTO));
        log.info("person saved : " + personDTO);
        notificationService.publishSavePersonEvent(personDTO.getName() + " saved");
        return personMapper.toDTO(savedPerson);
    }

    public void saveConcurrently(PersonDTO personDTO) {
        try {
            CompletableFuture.runAsync(() -> {
                log.info("thread : name : " + Thread.currentThread().getName() + " ,id : " + Thread.currentThread().getId() + " is running");
                save(personDTO);
                log.info("person saved concurrently with name : {}", personDTO.getName());
            }, executorService);
        } catch (Exception e) {
            log.error("Error in save person Concurrently with name : " + personDTO.getName(), e);
        }
    }

    public PersonDTO update(PersonDTO personDTO) {
        Person person = findEntityById(personDTO.getId());
        person.setName(personDTO.getName());

        PersonDTO savedPersonDTO = personMapper.toDTO(personRepository.save(person));
        log.info("person updated : " + savedPersonDTO);
        return savedPersonDTO;
    }

    public void delete(Long id) {
        Person person = findEntityById(id);
        personRepository.delete(person);
        log.info("person with id : {} deleted", id);
    }

    private Person findEntityById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent())
            return person.get();
        else
            throw new PersonNotFoundException("person with id : " + id + " not found");
    }
}
