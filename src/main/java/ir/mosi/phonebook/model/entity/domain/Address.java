package ir.mosi.phonebook.model.entity.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "address_id", unique = true)
    private Long addressId;
    // شناسه آدرس

    @Column(name = "description")
    private String description;
    // آدرس

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
    // شخص
}
