package ir.mostafa.semnani.phonebook.security.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tbl_app_permission")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AppPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @ManyToMany
    @JoinTable(
            name = "roles_permissions",
            joinColumns = @JoinColumn(name = "app_permission_id"),
            inverseJoinColumns = @JoinColumn(name = "app_role_id"))
    @JsonIgnore
    private Set<AppRole> appRoles;
}
