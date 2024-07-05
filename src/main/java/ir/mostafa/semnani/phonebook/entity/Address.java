package ir.mostafa.semnani.phonebook.entity;

import lombok.*;

import jakarta.persistence.*;

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
