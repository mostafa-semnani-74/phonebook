package ir.mostafa.semnani.phonebook.entity;

import ir.mostafa.semnani.phonebook.entity.base.BaseEntity;
import lombok.*;

import jakarta.persistence.*;
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

    @Column
    private Integer age;

    @Column(unique = true)
    private Long appUserId;

    @OneToMany(mappedBy = "person")
    private List<Address> addressList;
}
