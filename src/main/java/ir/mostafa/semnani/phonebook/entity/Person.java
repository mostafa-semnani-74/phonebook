package ir.mostafa.semnani.phonebook.entity;

import ir.mostafa.semnani.phonebook.entity.base.BaseEntity;
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
public class Person extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "person")
    private List<Address> addressList;
}
