package ir.mostafa.semnani.phonebook.repository;

import ir.mostafa.semnani.phonebook.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
