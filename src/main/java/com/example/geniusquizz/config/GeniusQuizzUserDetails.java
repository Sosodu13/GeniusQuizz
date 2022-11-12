package com.example.geniusquizz.config;

import com.example.geniusquizz.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;

public class GeniusQuizzUserDetails implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1;
    private final User user;

    public GeniusQuizzUserDetails(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public String getFullName()
    {
        return this.user.getFirstName()+ " " +user.getLastName();
    }

    public void setFirstName(String firstName)
    {
        this.user.setFirstName(firstName);
    }
}
