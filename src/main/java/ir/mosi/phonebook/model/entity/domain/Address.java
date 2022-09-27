package ir.mosi.phonebook.model.entity.domain;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
public class Address {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "address_id", unique = true)
    private Long addressId;
    // شناسه آدرس

    @NotNull
    @Size(max = 300, message = "address description must be less than 300 characters")
    @Column(name = "description", length = 300 , nullable = false)
    private String description;
    // آدرس

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
    // شخص


    public Address(Long id, Long addressId, String description) {
        this.id = id;
        this.addressId = addressId;
        this.description = description;
    }

}
