package ir.mostafa.semnani.phonebook.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AppUserDetails implements UserDetails {
    private final String username;
    private final String password;
    private final List<? extends GrantedAuthority> grantedAuthorities;
    private final boolean isAccountNotExpired;
    private final boolean isAccountNotNotLocked;
    private final boolean isCredentialNotExpired;
    private final boolean isEnabled;

    public AppUserDetails(String username, String password, List<? extends GrantedAuthority> grantedAuthorities,
                          boolean isAccountNotExpired, boolean isAccountNotNotLocked, boolean isCredentialNotExpired, boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
        this.isAccountNotExpired = isAccountNotExpired;
        this.isAccountNotNotLocked = isAccountNotNotLocked;
        this.isCredentialNotExpired = isCredentialNotExpired;
        this.isEnabled = isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNotExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNotNotLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialNotExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
