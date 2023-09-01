package ir.mostafa.semnani.phonebook.model.service;

import ir.mostafa.semnani.phonebook.model.dto.PersonDTO;

import java.util.List;

public interface PersonService {
    List<PersonDTO> findAll();

    PersonDTO findById(Long id);

    void save(PersonDTO personDTO);

    PersonDTO update(PersonDTO personDTO);

    void delete(Long id);
}
