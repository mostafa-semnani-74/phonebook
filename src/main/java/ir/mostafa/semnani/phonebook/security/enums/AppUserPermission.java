package ir.mostafa.semnani.phonebook.security.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AppUserPermission {
    PERSON_READ("person:read"),
    PERSON_WRITE("person:write");

    private final String permission;
}
