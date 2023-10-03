package ir.mostafa.semnani.phonebook.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_person")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Person {
    @Id
    @GeneratedValue
    private Long Id;

    @Column
    private String name;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Address> addressList;
}
