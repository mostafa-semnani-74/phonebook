package ir.mostafa.semnani.phonebook.security.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tbl_app_role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "app_role_id"),
            inverseJoinColumns = @JoinColumn(name = "app_user_id"))
    @JsonIgnore
    Set<AppUser> users;
}
