package ir.mostafa.semnani.phonebook.model.mapper;

import ir.mostafa.semnani.phonebook.model.dto.AddressDTO;
import ir.mostafa.semnani.phonebook.model.entity.Address;

import java.util.List;
import java.util.stream.Collectors;

public class AddressMapper {
    public static AddressDTO toDTO(Address address) {
        return AddressDTO.builder()
                .Id(address.getId())
                .description(address.getDescription())
                .build();
    }

    public static List<AddressDTO> toDTOs(List<Address> addressList) {
        return addressList.stream()
                .map(AddressMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static Address toEntity(AddressDTO addressDTO) {
        return Address.builder()
                .Id(addressDTO.getId())
                .description(addressDTO.getDescription())
                .build();
    }

    public static List<Address> toEntities(List<AddressDTO> addressDTOS) {
        return addressDTOS.stream()
                .map(AddressMapper::toEntity)
                .collect(Collectors.toList());
    }
}
