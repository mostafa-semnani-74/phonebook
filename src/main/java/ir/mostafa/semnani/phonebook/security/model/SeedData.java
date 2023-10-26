package ir.mostafa.semnani.phonebook.security.model;

import ir.mostafa.semnani.phonebook.security.enums.AppUserRole;
import ir.mostafa.semnani.phonebook.security.model.dto.AppUserDTO;
import ir.mostafa.semnani.phonebook.security.model.entity.AppRole;
import ir.mostafa.semnani.phonebook.security.model.entity.AppUser;
import ir.mostafa.semnani.phonebook.security.model.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class SeedData implements CommandLineRunner {
    private final AppUserService appUserService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SeedData(AppUserService appUserService, PasswordEncoder passwordEncoder) {
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        AppUserDTO admin = new AppUserDTO();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("123"));

        AppRole adminAppRole = new AppRole();
        adminAppRole.setName("ROLE_" + AppUserRole.ADMIN.name());
        admin.setRoles(Set.of(adminAppRole));

        appUserService.save(admin);

        AppUserDTO user = new AppUserDTO();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("123"));

        AppRole userAppRole = new AppRole();
        userAppRole.setName("ROLE_" + AppUserRole.PERSON.name());
        user.setRoles(Set.of(userAppRole));

        appUserService.save(user);
    }
}
