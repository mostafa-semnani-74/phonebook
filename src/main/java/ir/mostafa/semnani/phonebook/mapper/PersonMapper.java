package ir.mostafa.semnani.phonebook.mapper;

import ir.mostafa.semnani.phonebook.dto.PersonDTO;
import ir.mostafa.semnani.phonebook.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonMapper {
    public static PersonDTO toDTO(Person person) {
        return PersonDTO.builder()
                .id(person.getId())
                .name(person.getName())
                .age(person.getAge())
                .build();
    }

    public static List<PersonDTO> toDTOs(List<Person> persons) {
        return persons.stream()
                .map(PersonMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static Person toEntity(PersonDTO personDTO) {
        if (personDTO != null) {
            return Person.builder()
                    .id(personDTO.getId())
                    .name(personDTO.getName())
                    .age(personDTO.getAge())
                    .build();
        } else
            return new Person();
    }
}
