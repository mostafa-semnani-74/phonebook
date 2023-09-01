package ir.mostafa.semnani.phonebook.model.service.impl;

import ir.mostafa.semnani.phonebook.model.dto.PersonDTO;
import ir.mostafa.semnani.phonebook.model.entity.Person;
import ir.mostafa.semnani.phonebook.model.mapper.PersonMapper;
import ir.mostafa.semnani.phonebook.model.repository.PersonRepository;
import ir.mostafa.semnani.phonebook.model.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@AllArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private final PersonRepository personRepository;

    @Transactional(readOnly = true)
    public List<PersonDTO> findAll() {
        return PersonMapper.toDTOs(personRepository.findAll());
    }

    @Transactional(readOnly = true)
    public PersonDTO findById(Long id) {
        return PersonMapper.toDTO(personRepository.findById(id));
    }

    public void save(PersonDTO personDTO) {
        personRepository.save(PersonMapper.toEntity(personDTO));
    }

    public PersonDTO update(PersonDTO personDTO) {
        return PersonMapper.toDTO(
                personRepository.update(PersonMapper.toEntity(personDTO))
        );
    }

    public void delete(Long id) {
        personRepository.delete(id);
    }
}
