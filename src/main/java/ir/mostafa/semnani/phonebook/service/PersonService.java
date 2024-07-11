package ir.mostafa.semnani.phonebook.service;

import ir.mostafa.semnani.phonebook.dto.PageDTO;
import ir.mostafa.semnani.phonebook.dto.PersonCriteriaDTO;
import ir.mostafa.semnani.phonebook.dto.PersonDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PersonService {
    Page<PersonDTO> findAll(PageDTO pageDTO, PersonCriteriaDTO personCriteriaDTO);

    PersonDTO findById(Long id);

    void save(PersonDTO personDTO);

    void saveConcurrently(PersonDTO personDTO);

    PersonDTO update(PersonDTO personDTO);

    void delete(Long id);
}
