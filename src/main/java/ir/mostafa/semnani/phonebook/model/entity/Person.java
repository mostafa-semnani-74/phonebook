package ir.mostafa.semnani.phonebook.model.entity;

import lombok.*;

import javax.persistence.*;

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
}
