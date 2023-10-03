package ir.mostafa.semnani.phonebook.model.repository;

import ir.mostafa.semnani.phonebook.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
