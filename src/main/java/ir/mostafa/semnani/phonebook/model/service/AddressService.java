package ir.mostafa.semnani.phonebook.model.service;

import ir.mostafa.semnani.phonebook.model.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    List<AddressDTO> findAll();

    AddressDTO findById(Long id);

    void save(AddressDTO addressDTO);

    void saveAll(List<AddressDTO> addressDTOS);

    AddressDTO update(AddressDTO addressDTO);

    void delete(Long id);

}
