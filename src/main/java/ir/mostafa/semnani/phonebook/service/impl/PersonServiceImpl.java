package ir.mostafa.semnani.phonebook.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import ir.mostafa.semnani.phonebook.dto.PageDTO;
import ir.mostafa.semnani.phonebook.dto.PersonCriteriaDTO;
import ir.mostafa.semnani.phonebook.entity.QPerson;
import ir.mostafa.semnani.phonebook.exception.PersonNotFoundException;
import ir.mostafa.semnani.phonebook.dto.PersonDTO;
import ir.mostafa.semnani.phonebook.entity.Person;
import ir.mostafa.semnani.phonebook.mapper.PersonMapper;
import ir.mostafa.semnani.phonebook.repository.PersonRepository;
import ir.mostafa.semnani.phonebook.service.PersonNotificationService;
import ir.mostafa.semnani.phonebook.service.PersonService;
import ir.mostafa.semnani.phonebook.util.I18nUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
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

    private final PersonMapper personMapper;

    private final I18nUtils i18nUtils;

    private static final ExecutorService executorService = Executors.newFixedThreadPool(2);

    @Transactional(readOnly = true)
    public Page<PersonDTO> findAll(PageDTO pageDTO, PersonCriteriaDTO personCriteriaDTO) {
        Pageable pageable = PageRequest.of(pageDTO.pageNumber(), pageDTO.size());

        QPerson qPerson = QPerson.person;
        BooleanExpression where = Expressions.TRUE.isTrue();

        if (personCriteriaDTO.getIsAdult() != null) {
            if (personCriteriaDTO.getIsAdult())
                where = qPerson.age.goe(18);
            else
                where = qPerson.age.loe(18);
        }

        if (personCriteriaDTO.getName() != null)
            where = where.and(qPerson.name.startsWith(personCriteriaDTO.getName()));

        Page<Person> personList = personRepository.findAll(where, pageable);

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

    public PersonDTO saveConcurrently(PersonDTO personDTO) {
        try {
            CompletableFuture<PersonDTO> savedPersonCompletableFuture = CompletableFuture.supplyAsync(() -> {
                log.info("thread : name : " + Thread.currentThread().getName() + " ,id : " + Thread.currentThread().getId() + " is running");
                PersonDTO savedPerson = save(personDTO);
                log.info("person saved concurrently with name : {}", personDTO.getName());
                return savedPerson;
            }, executorService);

            return savedPersonCompletableFuture.join();
        } catch (Exception e) {
            log.error("Error in save person Concurrently with name : " + personDTO.getName(), e);
            throw new RuntimeException("error in save person concurrently");
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
            throw new PersonNotFoundException(String.format(i18nUtils.getMessage("person.not.found.exception"), id));
    }
}
