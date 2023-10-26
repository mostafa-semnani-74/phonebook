package ir.mostafa.semnani.phonebook.security.model;

import ir.mostafa.semnani.phonebook.security.model.entity.AppRole;
import ir.mostafa.semnani.phonebook.security.model.entity.AppUser;
import ir.mostafa.semnani.phonebook.security.model.service.AppRoleService;
import ir.mostafa.semnani.phonebook.security.model.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private final AppUserService appUserService;
    private final AppRoleService appRoleService;

    @Autowired
    public AppUserDetailsService(AppUserService appUserService, AppRoleService appRoleService) {
        this.appUserService = appUserService;
        this.appRoleService = appRoleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserService.findByUsername(username);
        List<AppRole> appRoles = appRoleService.findByUserId(appUser.getId());

        List<GrantedAuthority> authorities = appRoles.stream()
                .map(appRole -> new SimpleGrantedAuthority(appRole.getName()))
                .collect(Collectors.toList());

        AppUserDetails appUserDetails = new AppUserDetails(
                appUser.getUsername(), appUser.getPassword(), authorities,
                true, true, true, true
        );

        return appUserDetails;
    }
}
