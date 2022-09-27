package ir.mosi.phonebook.common.seed_data;

import ir.mosi.phonebook.model.entity.domain.Address;
import ir.mosi.phonebook.model.entity.domain.Person;
import ir.mosi.phonebook.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private PersonService personService;

    @Override
    public void run(String... args) throws Exception {
        Address address1 = new Address(1L,1L,"iran");
        Set<Address> addressSet1 = new HashSet<>();
        addressSet1.add(address1);
        Person person1 = new Person(1L,1L,"mostafa","semnani","09351231212",
                addressSet1);
        System.out.println("seed data completed");
    }
}
