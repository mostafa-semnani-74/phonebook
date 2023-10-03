package ir.mostafa.semnani.phonebook.model.service;

import ir.mostafa.semnani.phonebook.model.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    public List<AddressDTO> findAll();

    public AddressDTO findById(Long id);

    public void save(AddressDTO addressDTO);

    public AddressDTO update(AddressDTO addressDTO);

    public void delete(Long id);

}
