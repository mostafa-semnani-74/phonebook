package ir.mostafa.semnani.phonebook.model.service.impl;

import ir.mostafa.semnani.phonebook.model.dto.PersonDTO;
import ir.mostafa.semnani.phonebook.model.entity.Person;
import ir.mostafa.semnani.phonebook.model.mapper.PersonMapper;
import ir.mostafa.semnani.phonebook.model.repository.PersonRepository;
import ir.mostafa.semnani.phonebook.model.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@AllArgsConstructor
@Service
@Slf4j
public class PersonServiceImpl implements PersonService {
    @Autowired
    private final PersonRepository personRepository;

    @Transactional(readOnly = true)
    public List<PersonDTO> findAll() {
        List<Person> personList = personRepository.findAll();
        log.info("{} persons found", personList.size());
        return PersonMapper.toDTOs(personList);
    }

    @Transactional(readOnly = true)
    public PersonDTO findById(Long id) {
        Person person = personRepository.findById(id);
        log.info("person with id : {} found", person.getId());
        return PersonMapper.toDTO(person);
    }

    public void save(PersonDTO personDTO) {
        personRepository.save(PersonMapper.toEntity(personDTO));
        log.info("person saved : " + personDTO);
    }

    public PersonDTO update(PersonDTO personDTO) {
        PersonDTO savedPersonDTO = PersonMapper.toDTO(personRepository.update(PersonMapper.toEntity(personDTO)));
        log.info("person updated : " + savedPersonDTO);
        return savedPersonDTO;
    }

    public void delete(Long id) {
        personRepository.delete(id);
        log.info("person with id : {} deleted", id);
    }
}
