package ir.mostafa.semnani.phonebook.security.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tbl_app_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    private Set<AppRole> roles;
}
