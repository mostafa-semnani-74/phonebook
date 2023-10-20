package ir.mostafa.semnani.phonebook.security.model.dto;

import ir.mostafa.semnani.phonebook.security.model.entity.AppRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDTO {
    private Long id;
    private String username;
    private String password;
    Set<AppRole> roles;
}
