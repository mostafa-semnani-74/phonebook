package ir.mostafa.semnani.phonebook.mapper;

import ir.mostafa.semnani.phonebook.dto.AddressDTO;
import ir.mostafa.semnani.phonebook.entity.Address;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDTO toDTO(Address address);

    List<AddressDTO> toDTOs(List<Address> addressList);

    Address toEntity(AddressDTO addressDTO);

    List<Address> toEntities(List<AddressDTO> addressDTOS);
}
