package com.nc.finalproject.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Entity for authorization.
 *
 * @author WildDed
 * @version 1.0
 */

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(length = 32)
    private String username;

    @Column(nullable = false, length = 20)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Transient
    private String confirmPassword;

    @NonNull
    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "serviceOwner")
    private List<VetServiceOfUser> vetServicesOfUser = new ArrayList<>();

    @OneToMany(mappedBy = "doctor")
    private List<VetServiceOfUser> vetServicesOfDoctor = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    private List<Pet> pets = new ArrayList<>();

    public User(String username) {
        this.username = username;
    }

}
