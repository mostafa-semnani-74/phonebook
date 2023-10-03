package ir.mostafa.semnani.phonebook.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_address")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Address {
    @Id
    @GeneratedValue
    private Long Id;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;
}
