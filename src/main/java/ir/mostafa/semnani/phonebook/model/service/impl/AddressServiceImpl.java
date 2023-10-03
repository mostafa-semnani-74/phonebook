package ir.mostafa.semnani.phonebook.model.service.impl;

import ir.mostafa.semnani.phonebook.exception.AddressNotFoundException;
import ir.mostafa.semnani.phonebook.model.dto.AddressDTO;
import ir.mostafa.semnani.phonebook.model.entity.Address;
import ir.mostafa.semnani.phonebook.model.mapper.AddressMapper;
import ir.mostafa.semnani.phonebook.model.repository.AddressRepository;
import ir.mostafa.semnani.phonebook.model.service.AddressService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService {
    @Autowired
    private final AddressRepository addressRepository;

    @Transactional(readOnly = true)
    public List<AddressDTO> findAll() {
        List<Address> addressList = addressRepository.findAll();
        log.info("{} addresses found", addressList.size());
        return AddressMapper.toDTOs(addressList);
    }

    @Transactional(readOnly = true)
    public AddressDTO findById(Long id) {
        Address address = findEntityById(id);
        log.info("address with id : {} found", address.getId());
        return AddressMapper.toDTO(address);
    }

    public void save(AddressDTO addressDTO) {
        addressRepository.save(AddressMapper.toEntity(addressDTO));
        log.info("address saved : " + addressDTO);
    }

    public AddressDTO update(AddressDTO addressDTO) {
        Address address = findEntityById(addressDTO.getId());
        address.setDescription(addressDTO.getDescription());

        AddressDTO saveAddressDTO = AddressMapper.toDTO(addressRepository.save(address));
        log.info("person updated : " + saveAddressDTO);
        return saveAddressDTO;
    }

    public void delete(Long id) {
        Address address = findEntityById(id);
        addressRepository.delete(address);
        log.info("address with id : {} deleted", id);
    }

    private Address findEntityById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent())
            return address.get();
        else
            throw new AddressNotFoundException("address with id : " + id + " not found");
    }
}
