package ir.mostafa.semnani.phonebook.service.impl;

import ir.mostafa.semnani.phonebook.exception.AddressNotFoundException;
import ir.mostafa.semnani.phonebook.dto.AddressDTO;
import ir.mostafa.semnani.phonebook.entity.Address;
import ir.mostafa.semnani.phonebook.mapper.AddressMapper;
import ir.mostafa.semnani.phonebook.repository.AddressRepository;
import ir.mostafa.semnani.phonebook.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    private final AddressMapper addressMapper;

    @Transactional(readOnly = true)
    public List<AddressDTO> findAll() {
        List<Address> addressList = addressRepository.findAll();
        log.info("{} addresses found", addressList.size());
        return addressMapper.toDTOs(addressList);
    }

    @Transactional(readOnly = true)
    public AddressDTO findById(Long id) {
        Address address = findEntityById(id);
        log.info("address with id : {} found", address.getId());
        return addressMapper.toDTO(address);
    }

    public void save(AddressDTO addressDTO) {
        addressRepository.save(addressMapper.toEntity(addressDTO));
        log.info("address saved : " + addressDTO);
    }

    @Override
    public void saveAll(List<AddressDTO> addressDTOS) {
        addressRepository.saveAll(addressMapper.toEntities(addressDTOS));
        log.info("addresses saved : " + addressDTOS);
    }

    public AddressDTO update(AddressDTO addressDTO) {
        Address address = findEntityById(addressDTO.getId());
        address.setDescription(addressDTO.getDescription());

        AddressDTO saveAddressDTO = addressMapper.toDTO(addressRepository.save(address));
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
