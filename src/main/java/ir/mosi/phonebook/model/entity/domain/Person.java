package ir.mosi.phonebook.model.entity.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "person_id", unique = true)
    private Long personId;
    // شناسه شخص

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;
    //نام

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;
    // نام خانوادگی

    @NotNull
    @Size(min = 11, max = 11, message = "mobile number must be 11 digit")
    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;
    // شماره همراه

    @OneToMany
    @JoinColumn(name = "address_Id")
    private Set<Address> addresses;
    // آدرس

}
