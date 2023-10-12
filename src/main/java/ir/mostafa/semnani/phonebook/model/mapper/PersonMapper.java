package ir.mostafa.semnani.phonebook.model.mapper;

import ir.mostafa.semnani.phonebook.model.dto.PersonDTO;
import ir.mostafa.semnani.phonebook.model.entity.Person;

import java.util.List;
import java.util.stream.Collectors;

public class PersonMapper {
    public static PersonDTO toDTO(Person person) {
        return PersonDTO.builder()
                .id(person.getId())
                .name(person.getName())
                .addresses(AddressMapper.toDTOs(person.getAddressList()))
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
                    .addressList(AddressMapper.toEntities(personDTO.getAddresses()))
                    .build();
        } else
            return new Person();
    }
}
