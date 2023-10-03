package ir.mostafa.semnani.phonebook.model.service.impl;

import ir.mostafa.semnani.phonebook.exception.PersonNotFoundException;
import ir.mostafa.semnani.phonebook.model.dto.PersonDTO;
import ir.mostafa.semnani.phonebook.model.entity.Person;
import ir.mostafa.semnani.phonebook.model.mapper.PersonMapper;
import ir.mostafa.semnani.phonebook.model.repository.PersonRepository;
import ir.mostafa.semnani.phonebook.model.service.PersonNotificationService;
import ir.mostafa.semnani.phonebook.model.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@AllArgsConstructor
@Service
@Slf4j
public class PersonServiceImpl implements PersonService {
    @Autowired
    private final PersonRepository personRepository;
    @Autowired
    private final PersonNotificationService notificationService;

    @Transactional(readOnly = true)
    public List<PersonDTO> findAll() {
        List<Person> personList = personRepository.findAll();
        log.info("{} persons found", personList.size());
        return PersonMapper.toDTOs(personList);
    }

    @Transactional(readOnly = true)
    public PersonDTO findById(Long id) {
        Person person = findEntityById(id);
        log.info("person with id : {} found", person.getId());
        return PersonMapper.toDTO(person);
    }

    public void save(PersonDTO personDTO) {
        personRepository.save(PersonMapper.toEntity(personDTO));
        log.info("person saved : " + personDTO);
        notificationService.publishSavePersonEvent(personDTO.getName() + " saved");
    }

    public PersonDTO update(PersonDTO personDTO) {
        Person person = findEntityById(personDTO.getId());
        person.setName(personDTO.getName());

        PersonDTO savedPersonDTO = PersonMapper.toDTO(personRepository.save(person));
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
