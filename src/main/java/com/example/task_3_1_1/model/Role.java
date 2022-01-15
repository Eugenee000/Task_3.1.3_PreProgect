package com.example.task_3_1_1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> userSet;

    public Role() {
    }

    public Role(Long id, String name, List<User> userSet) {
        this.id = id;
        this.name = name;
        this.userSet = userSet;
    }

    public Role(String name, List<User> userSet) {
        this.name = name;
        this.userSet = userSet;
    }

    public Role(String name) {
        if (name.contains("ADMIN")) {
            this.id = 2L;
        } else if (name.contains("USER")) {
            this.id = 1L;
        }
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(List<User> userSet) {
        this.userSet = userSet;
    }

    @Override
    public String getAuthority() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}