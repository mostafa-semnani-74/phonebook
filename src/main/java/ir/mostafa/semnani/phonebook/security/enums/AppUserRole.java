package ir.mostafa.semnani.phonebook.security.enums;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum AppUserRole {
    ADMIN(Set.of(AppUserPermission.PERSON_WRITE, AppUserPermission.PERSON_READ)),
    PERSON(Set.of(AppUserPermission.PERSON_READ));

    private final Set<AppUserPermission> permissions;

    public Set<AppUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(p -> new SimpleGrantedAuthority(p.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
