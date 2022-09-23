package ir.mosi.phonebook.model.entity.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "person_id", unique = true)
    private Long personId;
    // شناسه شخص

    @Column(name = "first_name")
    private String firstName;
    //نام

    @Column(name = "last_name")
    private String lastName;
    // نام خانوادگی

    @Column(name = "phone_number")
    private String mobileNumber;
    // شماره همراه

    @OneToMany
    @JoinColumn(name = "address_Id")
    private Set<Address> addresses;
    // آدرس

}
