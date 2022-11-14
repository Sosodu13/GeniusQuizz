package com.example.geniusquizz.web.dto;

import com.example.geniusquizz.model.Role;
import com.example.geniusquizz.model.Session;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

public class UserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long Id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String githubInfo;
    private Set<Role> roles;
    private Set<Session> sessions;

    public UserDto(String firstName, String lastName, String email, String password, Set<Role> roles, Set<Session> sessions) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.sessions = sessions;
    }

    public UserDto() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGithubInfo() {
        return githubInfo;
    }

    public void setGithubInfo(String githubInfo) {
        this.githubInfo = githubInfo;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "Id=" + Id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", githubInfo='" + githubInfo + '\'' +
                ", roles=" + roles +
                ", sessions=" + sessions +
                '}';
    }
}