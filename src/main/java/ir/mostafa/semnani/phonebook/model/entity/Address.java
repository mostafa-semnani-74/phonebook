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
    private Long id;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;
}
